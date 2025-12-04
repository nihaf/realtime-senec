package de.codematrosen.rts;

import static de.codematrosen.rts.MainActivity.FORMAT_WATT;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.progressindicator.CircularProgressIndicator;

import java.util.Timer;
import java.util.TimerTask;

import de.codematrosen.rts.application.SenecPreferences;
import de.codematrosen.rts.application.converter.HexConverter;
import de.codematrosen.rts.infrastructure.SenecService;
import de.codematrosen.rts.infrastructure.SenecServiceGenerator;
import de.codematrosen.rts.infrastructure.dtos.EnergyReducedDto;
import de.codematrosen.rts.infrastructure.dtos.SenecEnergyRequestDto;
import de.codematrosen.rts.infrastructure.dtos.SenecEnergyResponseDto;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Activity that displays detailed battery information.
 * Shows power, voltage, current, and state of charge.
 * Values are refreshed periodically while the activity is visible.
 */
public class BatteryDetailActivity extends AppCompatActivity {

    private static final String TAG = BatteryDetailActivity.class.getSimpleName();

    private SenecService senecService;
    private Timer refreshTimer;
    private boolean isFirstLoad = true;

    private CircularProgressIndicator loadingIndicator;
    private LinearLayout dataContainer;
    private TextView textError;
    private TextView textBatteryState;
    private TextView textValuePower;
    private TextView textValueVoltage;
    private TextView textValueCurrent;
    private TextView textValueSoc;
    private ImageView imageBattery;

    private int colorRed;
    private int colorGreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery_detail);

        // Enable back button in action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.battery_detail_title);
        }

        colorRed = getResources().getColor(R.color.red, getApplicationContext().getTheme());
        colorGreen = getResources().getColor(R.color.green, getApplicationContext().getTheme());

        // Initialize views
        loadingIndicator = findViewById(R.id.progress_indicator);
        dataContainer = findViewById(R.id.data_container);
        textError = findViewById(R.id.text_error);
        textBatteryState = findViewById(R.id.text_battery_state);
        textValuePower = findViewById(R.id.text_value_power);
        textValueVoltage = findViewById(R.id.text_value_voltage);
        textValueCurrent = findViewById(R.id.text_value_current);
        textValueSoc = findViewById(R.id.text_value_soc);
        imageBattery = findViewById(R.id.image_battery);

        // Initialize service
        SenecPreferences senecPreferences = new SenecPreferences(this);
        String senecUrl = senecPreferences.getSenecUrl();
        senecService = SenecServiceGenerator.createService(senecUrl, SenecService.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        isFirstLoad = true;
        initRefreshTimer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        cancelRefreshTimer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancelRefreshTimer();
    }

    private void initRefreshTimer() {
        refreshTimer = new Timer("battery-refresh-timer");
        int refreshPeriod = getResources().getInteger(R.integer.energy_refresh_period_in_ms);
        refreshTimer.schedule(new RefreshBatteryDataTask(), 0, refreshPeriod);
    }

    private void cancelRefreshTimer() {
        if (refreshTimer != null) {
            refreshTimer.cancel();
            refreshTimer = null;
        }
    }

    private void fetchBatteryData() {
        if (isFirstLoad) {
            showLoading();
        }

        Call<SenecEnergyResponseDto> call = senecService.getEnergyData(new SenecEnergyRequestDto());
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<SenecEnergyResponseDto> call,
                                   @NonNull Response<SenecEnergyResponseDto> response) {
                if (response.isSuccessful() && response.body() != null) {
                    displayData(response.body());
                    isFirstLoad = false;
                } else {
                    Log.w(TAG, "Error fetching battery data: " + response.message());
                    if (isFirstLoad) {
                        showError();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<SenecEnergyResponseDto> call, @NonNull Throwable t) {
                Log.e(TAG, "Failed to fetch battery data", t);
                if (isFirstLoad) {
                    showError();
                }
            }
        });
    }

    private void displayData(SenecEnergyResponseDto data) {
        EnergyReducedDto energy = data.getEnergy();
        if (energy == null) {
            if (isFirstLoad) {
                showError();
            }
            return;
        }

        // Battery Power
        Float batteryPower = HexConverter.convertToFloat(energy.getGuiBatDataPower());
        if (batteryPower != null) {
            textValuePower.setText(FORMAT_WATT.format(Math.abs(batteryPower)));
            
            // Update icon color based on charging/discharging
            if (batteryPower < 0) {
                imageBattery.setColorFilter(colorRed); // Discharging
                textBatteryState.setText(R.string.status_battery_discharge);
            } else {
                imageBattery.setColorFilter(colorGreen); // Charging
                textBatteryState.setText(R.string.status_battery_charge);
            }
        }

        // Battery Voltage
        Float batteryVoltage = HexConverter.convertToFloat(energy.getGuiBatDataVoltage());
        if (batteryVoltage != null) {
            textValueVoltage.setText(FORMAT_WATT.format(batteryVoltage));
        }

        // Battery Current
        Float batteryCurrent = HexConverter.convertToFloat(energy.getGuiBatDataCurrent());
        if (batteryCurrent != null) {
            textValueCurrent.setText(FORMAT_WATT.format(Math.abs(batteryCurrent)));
        }

        // State of Charge
        Float soc = HexConverter.convertToFloat(energy.getGuiBatDataFuelCharge());
        if (soc != null) {
            int socInt = Math.round(soc);
            textValueSoc.setText(String.valueOf(socInt));
        }

        showData();
    }

    private void showLoading() {
        loadingIndicator.setVisibility(View.VISIBLE);
        dataContainer.setVisibility(View.GONE);
        textError.setVisibility(View.GONE);
    }

    private void showData() {
        loadingIndicator.setVisibility(View.GONE);
        dataContainer.setVisibility(View.VISIBLE);
        textError.setVisibility(View.GONE);
    }

    private void showError() {
        loadingIndicator.setVisibility(View.GONE);
        dataContainer.setVisibility(View.GONE);
        textError.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class RefreshBatteryDataTask extends TimerTask {
        @Override
        public void run() {
            BatteryDetailActivity.this.fetchBatteryData();
        }
    }
}
