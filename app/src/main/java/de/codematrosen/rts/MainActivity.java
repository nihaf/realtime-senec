package de.codematrosen.rts;

import static java.util.Objects.requireNonNull;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

import de.codematrosen.rts.application.converter.PowerUnitConverter;
import de.codematrosen.rts.infrastructure.SenecService;
import de.codematrosen.rts.infrastructure.SenecServiceGenerator;
import de.codematrosen.rts.infrastructure.dtos.SenecEnergyRequestDto;
import de.codematrosen.rts.infrastructure.dtos.SenecEnergyResponseDto;
import de.codematrosen.rts.infrastructure.dtos.converter.EnergyDtoConverter;
import de.codematrosen.rts.model.Energy;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private SenecService senecService;
    private TextView statusText;
    private TextView pvGenerationText;
    private TextView batteryPowerText;
    private TextView homeConsumptionText;
    private TextView gridPowerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        statusText = findViewById(R.id.text_value_status);
        pvGenerationText = findViewById(R.id.text_value_pv_generation);
        batteryPowerText = findViewById(R.id.text_value_battery_charge);
        homeConsumptionText = findViewById(R.id.text_value_home_consumption);
        gridPowerText = findViewById(R.id.text_value_grid_export);

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
                SenecEnergyResponseDto senecEnergyResponseDto = requireNonNull(response.body());
                Energy energy = EnergyDtoConverter.fromDto(senecEnergyResponseDto.getEnergy());
                statusText.setText(getResources().getStringArray(R.array.system_state_array)[energy.getStatState()]);
                pvGenerationText.setText(PowerUnitConverter.convert(energy.getGuiInverterPower()));
                batteryPowerText.setText(PowerUnitConverter.convert(energy.getGuiBatDataPower()));
                homeConsumptionText.setText(PowerUnitConverter.convert(energy.getGuiHousePow()));
                gridPowerText.setText(PowerUnitConverter.convert((energy.getGuiGridPow())));
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
            runOnUiThread(MainActivity.this::fetchSenecEnergyValues);
        }
    }
}
