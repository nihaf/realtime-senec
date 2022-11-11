package de.codematrosen.rts;

import static android.graphics.PorterDuff.Mode.SRC_IN;
import static java.util.Objects.requireNonNull;
import static de.codematrosen.rts.infrastructure.dtos.converter.EnergyDtoConverter.fromDto;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

import de.codematrosen.rts.infrastructure.SenecService;
import de.codematrosen.rts.infrastructure.SenecServiceGenerator;
import de.codematrosen.rts.infrastructure.dtos.SenecEnergyRequestDto;
import de.codematrosen.rts.infrastructure.dtos.SenecEnergyResponseDto;
import de.codematrosen.rts.model.Energy;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final DecimalFormat FORMAT = new DecimalFormat("#.#");

    private Timer fetchDataTimer;
    private SenecService senecService;
    private ImageView imageBattery;
    private ImageView imageGrid;
    private TextView textLabelBatteryPower;
    private TextView textLabelGridPower;
    private TextView textValueBatteryCurrent;
    private TextView textValueBatteryPower;
    private TextView textValueBatteryVoltage;
    private TextView textValueFuelGauge;
    private TextView textValueGridPower;
    private TextView textValueHomeConsumption;
    private TextView textValuePvGeneration;
    private TextView textValueStatus;
    private int colorRed;
    private int colorGreen;
    private int colorBlue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        colorRed = getResources().getColor(android.R.color.holo_red_dark, getApplicationContext().getTheme());
        colorGreen = getResources().getColor(android.R.color.holo_green_dark, getApplicationContext().getTheme());
        colorBlue = getResources().getColor(R.color.blue_800, getApplicationContext().getTheme());

        imageBattery = findViewById(R.id.image_battery);
        imageGrid = findViewById(R.id.image_grid);

        textLabelBatteryPower = findViewById(R.id.text_label_battery_charge);
        textLabelGridPower = findViewById(R.id.text_label_grid_export);
        textValueBatteryCurrent = findViewById(R.id.text_value_battery_current);
        textValueBatteryPower = findViewById(R.id.text_value_battery_charge);
        textValueBatteryVoltage = findViewById(R.id.text_value_battery_voltage);
        textValueFuelGauge = findViewById(R.id.text_value_fuel);
        textValueGridPower = findViewById(R.id.text_value_grid_export);
        textValueHomeConsumption = findViewById(R.id.text_value_home_consumption);
        textValuePvGeneration = findViewById(R.id.text_value_pv_generation);
        textValueStatus = findViewById(R.id.text_value_status);

        // TODO load baseUrl from shared preferences
        senecService = SenecServiceGenerator.createService("http://192.168.254.56", SenecService.class);
        initFetchDataTimer();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        initFetchDataTimer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        fetchDataTimer.cancel();
    }

    @Override
    protected void onPause() {
        super.onPause();
        fetchDataTimer.cancel();
    }

    private void initFetchDataTimer() {
        fetchDataTimer = new Timer("refresh-senec-timer");
        fetchDataTimer.scheduleAtFixedRate(new RefreshFieldsTask(), 0, getResources().getInteger(R.integer.energy_refresh_period_in_ms));
    }

    private void fetchSenecEnergyValues() {
        Call<SenecEnergyResponseDto> call = senecService.getEnergyData(new SenecEnergyRequestDto());

        call.enqueue(new Callback<SenecEnergyResponseDto>() {
            @Override
            public void onResponse(@NonNull Call<SenecEnergyResponseDto> call, @NonNull Response<SenecEnergyResponseDto> response) {
                if (response.isSuccessful()) {
                    Energy energy = fromDto(requireNonNull(response.body()).getEnergy());
                    textValueStatus.setText(getResources().getStringArray(R.array.system_state_array)[energy.getStatState()]);
                    textValueFuelGauge.setText(FORMAT.format(energy.getGuiBatDataFuelCharge()));
                    Float inverterPower = Math.abs(energy.getGuiInverterPower());
                    Float batteryPower = energy.getGuiBatDataPower();
                    Float batteryCurrent = energy.getGuiBatDataCurrent();
                    Float batteryVoltage = energy.getGuiBatDataVoltage();
                    Float housePower = energy.getGuiHousePow();
                    Float gridPower = energy.getGuiGridPow();

                    textValueBatteryCurrent.setText(FORMAT.format(batteryCurrent));
                    textValueBatteryPower.setText(FORMAT.format(Math.abs(batteryPower)));
                    textValueBatteryVoltage.setText(FORMAT.format(batteryVoltage));
                    textValueGridPower.setText(FORMAT.format(Math.abs(gridPower)));
                    textValueHomeConsumption.setText(FORMAT.format(housePower));
                    textValuePvGeneration.setText(FORMAT.format(inverterPower));

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
                    } else {
                        imageGrid.setColorFilter(colorGreen, SRC_IN);
                        textLabelGridPower.setText(getResources().getText(R.string.status_grid_export));
                    }
                } else {
                    Log.w(TAG, "" + response.errorBody());
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
