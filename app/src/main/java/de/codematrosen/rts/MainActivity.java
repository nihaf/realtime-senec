package de.codematrosen.rts;

import static android.graphics.PorterDuff.Mode.SRC_IN;
import static java.util.Objects.requireNonNull;
import static de.codematrosen.rts.application.converter.PowerUnitConverter.getUnitId;
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
    private static final DecimalFormat FORMAT = new DecimalFormat("#.##");

    private SenecService senecService;
    private TextView statusText;
    private TextView pvGenerationText;
    private TextView pvGenerationUnit;
    private TextView batteryPowerText;
    private TextView batteryPowerUnit;
    private TextView homeConsumptionText;
    private TextView homeConsumptionUnit;
    private TextView gridPowerText;
    private TextView gridPowerUnit;
    private ImageView batteryIconView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int colorGray = getResources().getColor(android.R.color.darker_gray);
        ((ImageView) findViewById(R.id.image_pv_generation)).setColorFilter(colorGray, SRC_IN);
        batteryIconView = findViewById(R.id.image_battery);
        batteryIconView.setColorFilter(colorGray, SRC_IN);
        ((ImageView) findViewById(R.id.image_home)).setColorFilter(colorGray, SRC_IN);
        ((ImageView) findViewById(R.id.image_grid)).setColorFilter(colorGray, SRC_IN);
        statusText = findViewById(R.id.text_value_status);
        pvGenerationText = findViewById(R.id.text_value_pv_generation);
        pvGenerationUnit = findViewById(R.id.text_unit_pv_generation);
        batteryPowerText = findViewById(R.id.text_value_battery_charge);
        batteryPowerUnit = findViewById(R.id.text_unit_battery_charge);
        homeConsumptionText = findViewById(R.id.text_value_home_consumption);
        homeConsumptionUnit = findViewById(R.id.text_unit_home_consumption);
        gridPowerText = findViewById(R.id.text_value_grid_export);
        gridPowerUnit = findViewById(R.id.text_unit_grid_export);

        // TODO load baseUrl from shared preferences
        senecService = SenecServiceGenerator.createService("http://192.168.254.56", SenecService.class);
        new Timer("refresh-senec-timer")
                .scheduleAtFixedRate(new RefreshFieldsTask(), 0, getResources().getInteger(R.integer.energy_refresh_period_in_ms));
    }

    private void fetchSenecEnergyValues() {
        Call<SenecEnergyResponseDto> call = senecService.getEnergyData(new SenecEnergyRequestDto());

        call.enqueue(new Callback<SenecEnergyResponseDto>() {
            @Override
            public void onResponse(@NonNull Call<SenecEnergyResponseDto> call, @NonNull Response<SenecEnergyResponseDto> response) {
                if (response.isSuccessful()) {
                    Energy energy = fromDto(requireNonNull(response.body()).getEnergy());
                    statusText.setText(getResources().getStringArray(R.array.system_state_array)[energy.getStatState()]);
                    Float inverterPower = energy.getGuiInverterPower();
                    Float batteryPower = energy.getGuiBatDataPower();
                    Float housePower = energy.getGuiHousePow();
                    Float gridPower = energy.getGuiGridPow();
                    pvGenerationText.setText(FORMAT.format(inverterPower));
                    pvGenerationUnit.setText(getResources().getText(getUnitId(inverterPower)));
                    batteryPowerText.setText(FORMAT.format(batteryPower));
                    batteryPowerUnit.setText(getResources().getText(getUnitId(batteryPower)));
                    homeConsumptionText.setText(FORMAT.format(housePower));
                    homeConsumptionUnit.setText(getResources().getText(getUnitId(housePower)));
                    gridPowerText.setText(FORMAT.format(gridPower));
                    gridPowerUnit.setText(getResources().getText(getUnitId(gridPower)));

                    if (energy.getGuiBoostingInfo()) {
                        batteryIconView.setColorFilter(getResources().getColor(android.R.color.holo_red_dark), SRC_IN);
                    } else if (energy.getGuiChargingInfo()) {
                        batteryIconView.setColorFilter(getResources().getColor(android.R.color.holo_green_dark), SRC_IN);
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
