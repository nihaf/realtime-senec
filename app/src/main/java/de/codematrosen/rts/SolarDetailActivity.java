package de.codematrosen.rts;

import static de.codematrosen.rts.MainActivity.FORMAT_WATT;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.progressindicator.CircularProgressIndicator;

import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

import de.codematrosen.rts.application.SenecPreferences;
import de.codematrosen.rts.application.converter.HexConverter;
import de.codematrosen.rts.infrastructure.SenecService;
import de.codematrosen.rts.infrastructure.SenecServiceGenerator;
import de.codematrosen.rts.infrastructure.dtos.Pm1ObjDto;
import de.codematrosen.rts.infrastructure.dtos.Pv1Dto;
import de.codematrosen.rts.infrastructure.dtos.SenecSolarRequestDto;
import de.codematrosen.rts.infrastructure.dtos.SenecSolarResponseDto;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Activity that displays detailed solar/PV inverter information.
 * Shows MPP power values from internal inverters and external power meter data.
 * Values are refreshed periodically while the activity is visible.
 */
public class SolarDetailActivity extends AppCompatActivity {

    private static final String TAG = SolarDetailActivity.class.getSimpleName();
    private SenecService senecService;
    private Timer refreshTimer;
    private boolean isFirstLoad = true;

    private CircularProgressIndicator progressIndicator;
    private LinearLayout dataContainer;
    private TextView textError;
    private TextView textValuePmTotal;
    private TextView textValueMppPower0;
    private TextView textValueMppPower1;
    private TextView textValueMppPower2;
    private TextView textValueTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solar_detail);

        // Enable back button in action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.solar_detail_title);
        }

        // Initialize views
        progressIndicator = findViewById(R.id.progress_indicator);
        dataContainer = findViewById(R.id.data_container);
        textError = findViewById(R.id.text_error);
        textValuePmTotal = findViewById(R.id.text_value_pm_total);
        textValueMppPower0 = findViewById(R.id.text_value_mpp_power_0);
        textValueMppPower1 = findViewById(R.id.text_value_mpp_power_1);
        textValueMppPower2 = findViewById(R.id.text_value_mpp_power_2);
        textValueTotal = findViewById(R.id.text_value_total);

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
        refreshTimer = new Timer("solar-refresh-timer");
        int refreshPeriod = getResources().getInteger(R.integer.energy_refresh_period_in_ms);
        refreshTimer.schedule(new RefreshSolarDataTask(), 0, refreshPeriod);
    }

    private void cancelRefreshTimer() {
        if (refreshTimer != null) {
            refreshTimer.cancel();
            refreshTimer = null;
        }
    }

    private void fetchSolarData() {
        if (isFirstLoad) {
            showLoading();
        }

        Call<SenecSolarResponseDto> call = senecService.getSolarData(new SenecSolarRequestDto());
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<SenecSolarResponseDto> call, 
                                   @NonNull Response<SenecSolarResponseDto> response) {
                if (response.isSuccessful() && response.body() != null) {
                    displayData(response.body());
                    isFirstLoad = false;
                } else {
                    Log.w(TAG, "Error fetching solar data: " + response.message());
                    if (isFirstLoad) {
                        showError();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<SenecSolarResponseDto> call, @NonNull Throwable t) {
                Log.e(TAG, "Failed to fetch solar data", t);
                if (isFirstLoad) {
                    showError();
                }
            }
        });
    }

    private void displayData(SenecSolarResponseDto data) {
        // Parse PM1OBJ2 total power
        Pm1ObjDto pm = data.getPowerMeter2();
        float totalPower = 0f;
        if (pm != null && pm.getTotalPower() != null) {
            Float pmTotal = HexConverter.convertToFloat(pm.getTotalPower());
            if (pmTotal != null) {
                float pmTotalAbs = Math.abs(pmTotal);
                textValuePmTotal.setText(FORMAT_WATT.format(pmTotalAbs));
                totalPower += pmTotalAbs;
            }
        }

        // Parse PV1 MPP power values
        Pv1Dto pv1 = data.getPv1();

        if (pv1 != null && pv1.getMppPower() != null) {
            String[] mppPower = pv1.getMppPower();
            
            if (mppPower.length > 0) {
                Float power0 = HexConverter.convertToFloat(mppPower[0]);
                if (power0 != null) {
                    textValueMppPower0.setText(FORMAT_WATT.format(power0));
                    totalPower += power0;
                }
            }
            
            if (mppPower.length > 1) {
                Float power1 = HexConverter.convertToFloat(mppPower[1]);
                if (power1 != null) {
                    textValueMppPower1.setText(FORMAT_WATT.format(power1));
                    totalPower += power1;
                }
            }
            
            if (mppPower.length > 2) {
                Float power2 = HexConverter.convertToFloat(mppPower[2]);
                if (power2 != null) {
                    textValueMppPower2.setText(FORMAT_WATT.format(power2));
                    totalPower += power2;
                }
            }
        }
        
        textValueTotal.setText(FORMAT_WATT.format(totalPower));

        showData();
    }

    private void showLoading() {
        progressIndicator.setVisibility(View.VISIBLE);
        dataContainer.setVisibility(View.GONE);
        textError.setVisibility(View.GONE);
    }

    private void showData() {
        progressIndicator.setVisibility(View.GONE);
        dataContainer.setVisibility(View.VISIBLE);
        textError.setVisibility(View.GONE);
    }

    private void showError() {
        progressIndicator.setVisibility(View.GONE);
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

    private class RefreshSolarDataTask extends TimerTask {
        @Override
        public void run() {
            SolarDetailActivity.this.fetchSolarData();
        }
    }
}
