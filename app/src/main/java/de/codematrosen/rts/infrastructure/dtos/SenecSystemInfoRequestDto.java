package de.codematrosen.rts.infrastructure.dtos;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

/**
 * Request DTO for fetching system information from SENEC backend.
 * Requests control unit info (WIZARD), system update info (SYS_UPDATE),
 * battery info (BAT1), and PV inverter info (PV1).
 */
public class SenecSystemInfoRequestDto extends SenecRequestDto {

    @SerializedName("FACTORY")
    private final Map<String, Object> factory;

    @SerializedName("WIZARD")
    private final Map<String, Object> wizard;

    @SerializedName("SYS_UPDATE")
    private final Map<String, Object> sysUpdate;

    @SerializedName("BAT1")
    private final Map<String, Object> battery;

    @SerializedName("IPU")
    private final Map<String, Object> ipu;

    @SerializedName("PV1")
    private final Map<String, Object> pv1;

    @SerializedName("ENERGY")
    private final Map<String, Object> energy;

    public SenecSystemInfoRequestDto() {
        factory = new HashMap<>();
        factory.put("DEVICE_ID","");

        wizard = new HashMap<>();
        // Control unit info
        wizard.put("APPLICATION_VERSION", "");
        wizard.put("DEVICE_BATTERY_TYPE", "");
        wizard.put("MAC_ADDRESS_BYTES", "");
        wizard.put("GUI_LANG", "");
        wizard.put("FIRMWARE_VERSION", "");
        wizard.put("INTERFACE_VERSION", "");

        sysUpdate = new HashMap<>();
        // System version info
        sysUpdate.put("NPU_IMAGE_VERSION", "");
        sysUpdate.put("NPU_VER", "");

        battery = new HashMap<>();
        // Battery/system info
        battery.put("SERIAL", "");
        battery.put("TYPE", "");

        ipu = new HashMap<>();
        ipu.put("VERSION", "");

        pv1 = new HashMap<>();
        pv1.put("VERSION", "");

        energy = new HashMap<>();
        energy.put("STAT_HOURS_OF_OPERATION", "");
    }
}
