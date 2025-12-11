package de.codematrosen.rts.infrastructure.dtos;

import com.google.gson.annotations.SerializedName;

/**
 * Reduced DTO for WIZARD section containing system information.
 */
public class WizardReducedDto {

    @SerializedName("APPLICATION_VERSION")
    private String applicationVersion;
    @SerializedName("FIRMWARE_VERSION")
    private String firmwareVersion;
    @SerializedName("INTERFACE_VERSION")
    private String interfaceVersion;

    @SerializedName("DEVICE_BATTERY_TYPE")
    private String deviceBatteryType;

    @SerializedName("MAC_ADDRESS_BYTES")
    private String[] macAddressBytes;

    @SerializedName("GUI_LANG")
    private String guiLang;

    public String getApplicationVersion() {
        return applicationVersion;
    }

    public void setApplicationVersion(String applicationVersion) {
        this.applicationVersion = applicationVersion;
    }

    public String getDeviceBatteryType() {
        return deviceBatteryType;
    }

    public void setDeviceBatteryType(String deviceBatteryType) {
        this.deviceBatteryType = deviceBatteryType;
    }

    public String[] getMacAddressBytes() {
        return macAddressBytes;
    }

    public void setMacAddressBytes(String[] macAddressBytes) {
        this.macAddressBytes = macAddressBytes;
    }

    public String getGuiLang() {
        return guiLang;
    }

    public void setGuiLang(String guiLang) {
        this.guiLang = guiLang;
    }

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    public String getInterfaceVersion() {
        return interfaceVersion;
    }

    public void setInterfaceVersion(String interfaceVersion) {
        this.interfaceVersion = interfaceVersion;
    }
}
