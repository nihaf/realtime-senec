package de.codematrosen.rts;

import static android.graphics.PorterDuff.Mode.SRC_IN;
import static java.util.Objects.requireNonNull;
import static de.codematrosen.rts.infrastructure.dtos.converter.EnergyDtoConverter.fromDto;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.progressindicator.CircularProgressIndicator;

import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

import de.codematrosen.rts.application.SenecPreferences;
import de.codematrosen.rts.infrastructure.SenecService;
import de.codematrosen.rts.infrastructure.SenecServiceGenerator;
import de.codematrosen.rts.infrastructure.dtos.SenecEnergyRequestDto;
import de.codematrosen.rts.infrastructure.dtos.SenecEnergyResponseDto;
import de.codematrosen.rts.model.Energy;
import de.codematrosen.rts.model.PowerMeter;
import de.codematrosen.rts.ui.CaretAnimationView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final DecimalFormat FORMAT_WATT = new DecimalFormat("#.0");
    private static final DecimalFormat FORMAT_KILOWATT = new DecimalFormat("#.00");

    private SenecPreferences senecPreferences;

    private Timer fetchDataTimer;
    private SenecService senecService;
    private ImageView imageBattery;
    private ImageView imageGrid;
    private CaretAnimationView gridCaretAnimation;
    private CircularProgressIndicator batteryProgressIndicator;
    private TextView textLabelBatteryPower;
    private TextView textLabelGridPower;
    private TextView textValueBatteryCurrent;
    private TextView textValueBatteryPower;
    private TextView textValueBatteryVoltage;
    private TextView textBatterySoc;
    private TextView textValueGridPower;
    private TextView textValueHomeConsumption;
    private TextView textValuePvGenerationEast;
    private TextView textValuePvGenerationWest;
    private TextView textValueStatus;
    private TextView textUnitPvGeneration;
    private TextView textUnitBatteryCharge;
    private TextView textUnitHomeConsumption;
    private TextView textUnitGridExport;
    private float divisor;
    private DecimalFormat format;

    private int colorRed;
    private int colorGreen;
    private int colorBlue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        senecPreferences = new SenecPreferences(this);

        colorRed = getResources().getColor(R.color.red, getApplicationContext().getTheme());
        colorGreen = getResources().getColor(R.color.green, getApplicationContext().getTheme());
        colorBlue = getResources().getColor(R.color.primary_sage_500, getApplicationContext().getTheme());

        imageBattery = findViewById(R.id.image_battery);
        imageGrid = findViewById(R.id.image_grid);
        gridCaretAnimation = findViewById(R.id.grid_caret_animation);
        batteryProgressIndicator = findViewById(R.id.battery_progress_indicator);

        textLabelBatteryPower = findViewById(R.id.text_label_battery_charge);
        textLabelGridPower = findViewById(R.id.text_label_grid_export);
        textValueBatteryCurrent = findViewById(R.id.text_value_battery_current);
        textValueBatteryPower = findViewById(R.id.text_value_battery_charge);
        textValueBatteryVoltage = findViewById(R.id.text_value_battery_voltage);
        textBatterySoc = findViewById(R.id.text_battery_soc);
        textValueGridPower = findViewById(R.id.text_value_grid_export);
        textValueHomeConsumption = findViewById(R.id.text_value_home_consumption);
        textValuePvGenerationWest = findViewById(R.id.text_value_pv_generation_west);
        textValuePvGenerationEast = findViewById(R.id.text_value_pv_generation_east);
        textValueStatus = findViewById(R.id.text_value_status);
        textUnitPvGeneration = findViewById(R.id.text_unit_pv_generation);
        textUnitBatteryCharge = findViewById(R.id.text_unit_battery_charge);
        textUnitHomeConsumption = findViewById(R.id.text_unit_home_consumption);
        textUnitGridExport = findViewById(R.id.text_unit_grid_export);

        // Set up click listener for PV generation squircle
        findViewById(R.id.squircle_pv_generation).setOnClickListener(v -> {
            Intent intent = new Intent(this, SolarDetailActivity.class);
            startActivity(intent);
        });

        setUnits();

        // Check if IP address is configured
        if (!senecPreferences.isIpConfigured()) {
            Toast.makeText(this, R.string.error_ip_not_configured, Toast.LENGTH_LONG).show();
            // Navigate to settings
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return;
        }
        String senecUrl = senecPreferences.getSenecUrl();

        senecService = SenecServiceGenerator.createService(senecUrl, SenecService.class);
        initFetchDataTimer();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUnits();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        senecService = SenecServiceGenerator.createService(senecPreferences.getSenecUrl(), SenecService.class);
        initFetchDataTimer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancelExistingTimer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        cancelExistingTimer();
    }

    private void setUnits() {
        boolean useKilowatts = senecPreferences.isUsingKilowatts();
        divisor = useKilowatts ? 1000.0f : 1.0f;
        format = useKilowatts ? FORMAT_KILOWATT : FORMAT_WATT;
        int unitStringRes = useKilowatts ? R.string.unit_kilowatt : R.string.unit_watt;
        textUnitPvGeneration.setText(unitStringRes);
        textUnitBatteryCharge.setText(unitStringRes);
        textUnitHomeConsumption.setText(unitStringRes);
        textUnitGridExport.setText(unitStringRes);
    }

    private void cancelExistingTimer() {
        if (fetchDataTimer != null) {
            fetchDataTimer.cancel();
        }
    }

    private void initFetchDataTimer() {
        fetchDataTimer = new Timer("refresh-senec-timer");
        fetchDataTimer.schedule(new RefreshFieldsTask(), 0, getResources().getInteger(R.integer.energy_refresh_period_in_ms));
    }

    private void fetchSenecEnergyValues() {
        Call<SenecEnergyResponseDto> energyCall = senecService.getEnergyData(new SenecEnergyRequestDto());

        energyCall.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<SenecEnergyResponseDto> call, @NonNull Response<SenecEnergyResponseDto> response) {
                if (response.isSuccessful()) {
                    SenecEnergyResponseDto responseDto = requireNonNull(response.body());
                    Energy energy = fromDto(responseDto.getEnergy());
                    PowerMeter powerMeter = fromDto(responseDto.getPowerMeter2());

                    textValueStatus.setText(getResources().getStringArray(R.array.system_state_array)[energy.getStatState()]);
                    int fuelCharge = energy.getGuiBatDataFuelCharge().intValue();
                    textBatterySoc.setText(Integer.toString(fuelCharge));
                    batteryProgressIndicator.setProgress(fuelCharge);
                    Float inverterPowerEast = Math.abs(powerMeter.getTotalPower());
                    float inverterPowerWest = Math.abs(energy.getGuiInverterPower() - inverterPowerEast);
                    Float batteryPower = energy.getGuiBatDataPower();
                    Float batteryCurrent = energy.getGuiBatDataCurrent();
                    Float batteryVoltage = energy.getGuiBatDataVoltage();
                    Float housePower = energy.getGuiHousePow();
                    Float gridPower = energy.getGuiGridPow();

                    textValueBatteryCurrent.setText(format.format(batteryCurrent));
                    textValueBatteryPower.setText(format.format(Math.abs(batteryPower) / divisor));
                    textValueBatteryVoltage.setText(format.format(batteryVoltage));
                    textValueGridPower.setText(format.format(Math.abs(gridPower) / divisor));
                    textValueHomeConsumption.setText(format.format(housePower / divisor));
                    textValuePvGenerationEast.setText(format.format(inverterPowerEast / divisor));
                    textValuePvGenerationWest.setText(format.format(inverterPowerWest / divisor));

                    if (energy.getGuiBoostingInfo()) {
                        imageBattery.setColorFilter(colorRed, SRC_IN);
                        textLabelBatteryPower.setText(getResources().getText(R.string.status_battery_discharge));
                    } else if (energy.getGuiChargingInfo()) {
                        imageBattery.setColorFilter(colorGreen, SRC_IN);
                        textLabelBatteryPower.setText(getResources().getText(R.string.status_battery_charge));
                    } else {
                        imageBattery.setColorFilter(colorBlue, SRC_IN);
                        textLabelBatteryPower.setText(getResources().getText(R.string.status_battery_charge));
                    }
                    if (gridPower > 0.0f) {
                        imageGrid.setColorFilter(colorRed, SRC_IN);
                        textLabelGridPower.setText(getResources().getText(R.string.status_grid_import));
                        // Animate carets flowing LEFT (importing from grid)
                        gridCaretAnimation.setDirection(CaretAnimationView.Direction.LEFT);
                    } else {
                        imageGrid.setColorFilter(colorGreen, SRC_IN);
                        textLabelGridPower.setText(getResources().getText(R.string.status_grid_export));
                        // Animate carets flowing RIGHT (exporting to grid)
                        gridCaretAnimation.setDirection(CaretAnimationView.Direction.RIGHT);
                    }
                    if (!gridCaretAnimation.isAnimating()) {
                        gridCaretAnimation.startAnimation();
                    }
                } else {
                    Log.w(TAG, "Error fetching data: " + response.message() + ", "+ response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<SenecEnergyResponseDto> call, Throwable t) {
                // ignore
            }
        });
    }

    private class RefreshFieldsTask extends TimerTask {
        @Override
        public void run() {
            MainActivity.this.fetchSenecEnergyValues();
        }
    }
}
