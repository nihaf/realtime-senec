package de.codematrosen.rts;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.progressindicator.CircularProgressIndicator;

import de.codematrosen.rts.application.SenecPreferences;
import de.codematrosen.rts.application.converter.HexConverter;
import de.codematrosen.rts.infrastructure.SenecService;
import de.codematrosen.rts.infrastructure.SenecServiceGenerator;
import de.codematrosen.rts.infrastructure.dtos.BatteryDto;
import de.codematrosen.rts.infrastructure.dtos.IpuDto;
import de.codematrosen.rts.infrastructure.dtos.Pv1Dto;
import de.codematrosen.rts.infrastructure.dtos.SenecSystemInfoRequestDto;
import de.codematrosen.rts.infrastructure.dtos.SenecSystemInfoResponseDto;
import de.codematrosen.rts.infrastructure.dtos.SystemUpdateDto;
import de.codematrosen.rts.infrastructure.dtos.WizardReducedDto;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Activity that displays system information about the SENEC device.
 * Shows control unit info, version revisions, and PV inverter details.
 */
public class SystemInformationActivity extends AppCompatActivity {

    private static final String TAG = SystemInformationActivity.class.getSimpleName();

    private SenecService senecService;

    private CircularProgressIndicator progressIndicator;
    private LinearLayout dataContainer;
    private TextView textError;

    // Control Unit fields
    private TextView textValueType;
    private TextView textValueSerial;
    private TextView textValueMac;
    private TextView textValueOperatingHours;

    // Revision fields
    private TextView textValueRevisionMcu;
    private TextView textValueRevisionMcuBl;
    private TextView textValueRevisionNpuRegs;
    private TextView textValueRevisionNpuImage;
    private TextView textValueRevisionGui;

    // PV Inverter fields
    private TextView textValueInverterLv;
    private TextView textValueInverterHv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_information);

        // Enable back button in action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.system_info_title);
        }

        // Initialize views
        progressIndicator = findViewById(R.id.progress_indicator);
        dataContainer = findViewById(R.id.data_container);
        textError = findViewById(R.id.text_error);

        // Control Unit fields
        textValueType = findViewById(R.id.text_value_type);
        textValueSerial = findViewById(R.id.text_value_serial);
        textValueMac = findViewById(R.id.text_value_mac);
        textValueOperatingHours = findViewById(R.id.text_value_operating_hours);

        // Revision fields
        textValueRevisionMcu = findViewById(R.id.text_value_revision_mcu);
        textValueRevisionMcuBl = findViewById(R.id.text_value_revision_mcu_bl);
        textValueRevisionNpuRegs = findViewById(R.id.text_value_revision_npu_regs);
        textValueRevisionNpuImage = findViewById(R.id.text_value_revision_npu_image);
        textValueRevisionGui = findViewById(R.id.text_value_revision_gui);

        // PV Inverter fields
        textValueInverterLv = findViewById(R.id.text_value_inverter_lv);
        textValueInverterHv = findViewById(R.id.text_value_inverter_hv);

        // Initialize service
        SenecPreferences senecPreferences = new SenecPreferences(this);
        String senecUrl = senecPreferences.getSenecUrl();
        senecService = SenecServiceGenerator.createService(senecUrl, SenecService.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchSystemInfo();
    }

    private void fetchSystemInfo() {
        showLoading();

        Call<SenecSystemInfoResponseDto> call = senecService.getSystemInfo(new SenecSystemInfoRequestDto());
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<SenecSystemInfoResponseDto> call,
                                   @NonNull Response<SenecSystemInfoResponseDto> response) {
                if (response.isSuccessful() && response.body() != null) {
                    displayData(response.body());
                } else {
                    Log.w(TAG, "Error fetching system info: " + response.message());
                    showError();
                }
            }

            @Override
            public void onFailure(@NonNull Call<SenecSystemInfoResponseDto> call, @NonNull Throwable t) {
                Log.e(TAG, "Failed to fetch system info", t);
                showError();
            }
        });
    }

    private void displayData(SenecSystemInfoResponseDto data) {
        // Process WIZARD data (Control Unit info)
        WizardReducedDto wizard = data.getWizard();
        if (wizard != null) {
            // Device Type from system type array
            Integer deviceType = HexConverter.convertToInteger(wizard.getDeviceBatteryType());
            if (deviceType != null) {
                String[] systemTypes = getResources().getStringArray(R.array.system_type);
                if (deviceType >= 0 && deviceType < systemTypes.length) {
                    textValueType.setText(systemTypes[deviceType]);
                }
            }

            // MAC Address
            String[] macBytes = wizard.getMacAddressBytes();
            if (macBytes != null && macBytes.length >= 6) {
                StringBuilder macBuilder = new StringBuilder();
                for (int i = 0; i < 6; i++) {
                    Integer byteValue = HexConverter.convertToInteger(macBytes[i]);
                    if (byteValue != null) {
                        if (i > 0) {
                            macBuilder.append("-");
                        }
                        macBuilder.append(String.format("%02X", byteValue));
                    }
                }
                textValueMac.setText(macBuilder.toString());
            }

            // Revision MCU (APPLICATION_VERSION)
            String mcuVersion = HexConverter.convertToString(wizard.getApplicationVersion());
            if (mcuVersion != null && !mcuVersion.isEmpty()) {
                textValueRevisionMcu.setText(mcuVersion);
            }

            // Revision MCU-BL (FIRMWARE_VERSION)
            String mcuBlVersion = HexConverter.convertToString(wizard.getFirmwareVersion());
            if (mcuBlVersion != null && !mcuBlVersion.isEmpty()) {
                textValueRevisionMcuBl.setText(mcuBlVersion);
            }

            // Revision GUI (INTERFACE_VERSION)
            String guiVersion = HexConverter.convertToString(wizard.getInterfaceVersion());
            if (guiVersion != null && !guiVersion.isEmpty()) {
                textValueRevisionGui.setText(guiVersion);
            }
        }

        // Process BAT1 data (Serial, Type info)
        BatteryDto battery = data.getBattery();
        if (battery != null) {
            // Serial Number
            String serial = HexConverter.convertToString(battery.getSerial());
            if (serial != null && !serial.isEmpty()) {
                textValueSerial.setText(serial);
            }
        }

        // Process SYS_UPDATE data (Version info)
        SystemUpdateDto sysUpdate = data.getSysUpdate();
        if (sysUpdate != null) {
            // NPU-Image version (numeric value)
            Integer npuImageVersion = HexConverter.convertToInteger(sysUpdate.getNpuImageVersion());
            if (npuImageVersion != null) {
                textValueRevisionNpuImage.setText(String.valueOf(npuImageVersion));
            }

            // NPU version (numeric value, used as NPU-Regs)
            Integer npuVer = HexConverter.convertToInteger(sysUpdate.getNpuVer());
            if (npuVer != null) {
                textValueRevisionNpuRegs.setText(String.valueOf(npuVer));
            }
        }

        // Process ENERGY data (Operating hours)
        de.codematrosen.rts.infrastructure.dtos.EnergyDto energy = data.getEnergy();
        if (energy != null) {
            // Operating hours
            Float hoursOfOperation = HexConverter.convertToFloat(energy.getStatHoursOfOperation());
            if (hoursOfOperation != null) {
                textValueOperatingHours.setText(String.format("%.0f h", hoursOfOperation));
            }
        }

        // Process PV1 data (Inverter versions)
        Pv1Dto pv1 = data.getPv1();
        if (pv1 != null) {
            String versions = pv1.getVersion();
            if (versions != null) {
                // Index 0: V3 LV inverter version
                if (!versions.isEmpty()) {
                    String lvVersion = HexConverter.convertToString(versions);
                    if (lvVersion != null && !lvVersion.isEmpty()) {
                        textValueInverterLv.setText(lvVersion);
                    }
                }
                // Index 1: V3 HV inverter version
//                if (versions.length > 1) {
//                    String hvVersion = HexConverter.convertToString(versions[1]);
//                    if (hvVersion != null && !hvVersion.isEmpty()) {
//                        textValueInverterHv.setText(hvVersion);
//                    }
//                }
            }
        }

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
}
