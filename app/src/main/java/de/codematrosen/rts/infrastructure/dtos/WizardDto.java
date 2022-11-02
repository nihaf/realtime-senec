package de.codematrosen.rts.infrastructure.dtos;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WizardDto {

    @SerializedName("APPLICATION_HASH")
    private String applicationHash;
    @SerializedName("APPLICATION_VERSION")
    private String applicationVersion;
    @SerializedName("BOOT")
    private String boot;
    @SerializedName("CHARGE_PRIO")
    private String chargePrio;
    @SerializedName("CONFIG_CHECKSUM")
    private String configChecksum;
    @SerializedName("CONFIG_LOADED")
    private String configLoaded;
    @SerializedName("CONFIG_MODIFIED_BY_USER")
    private String configModifiedByUser;
    @SerializedName("CONFIG_WRITE")
    private String configWrite;
    @SerializedName("DEVICE_BATTERY_TYPE")
    private String deviceBatteryType;
    @SerializedName("DEVICE_INVERTER_TYPE")
    private String deviceInverterType;
    @SerializedName("DEVICE_INV_ENABLED")
    private String[] deviceInvEnabled;
    @SerializedName("DEVICE_INV_PHASES_ARR")
    private String[] deviceInvPhasesArr;
    @SerializedName("DEVICE_INV_SLAVE_ADRESS")
    private String[] deviceInvSlaveAdress;
    @SerializedName("DEVICE_PM_GRID_ENABLED")
    private String devicePmGridEnabled;
    @SerializedName("DEVICE_PM_HOUSE_ENABLED")
    private String devicePmHouseEnabled;
    @SerializedName("DEVICE_PM_TYPE")
    private String devicePmType;
    @SerializedName("DEVICE_WB_TYPE")
    private String deviceWbType;
    @SerializedName("FEATURECODE_ENTERED")
    private String featurecodeEntered;
    @SerializedName("FIRMWARE_VERSION")
    private String firmwareVersion;
    @SerializedName("GENERATION_METER_SN")
    private String generationMeterSn;
    @SerializedName("GRID_CONNECTION_TYPE")
    private String grid_connectionType;
    @SerializedName("GUI_LANG")
    private String guiLang;
    @SerializedName("HEATPUMP_METER_SN")
    private String heatpumpMeterSn;
    @SerializedName("HEAT_CONN_TYPE")
    private String heatConnType;
    @SerializedName("INSULATION_RESISTANCE")
    private String insulationResistance;
    @SerializedName("INTERFACE_VERSION")
    private String interfaceVersion;
    @SerializedName("MAC_ADDRESS_BYTES")
    private String[] macAddressBytes;
    @SerializedName("MASTER_SLAVE_ADDRESSES")
    private String masterSlaveAddresses;
    @SerializedName("MASTER_SLAVE_MODE")
    private String masterSlaveMode;
    @SerializedName("POWER_METER_SERIAL")
    private String powerMeterSerial;
    @SerializedName("PS_ENABLE")
    private String psEnable;
    @SerializedName("PS_HOUR")
    private String psHour;
    @SerializedName("PS_MINUTE")
    private String psMinute;
    @SerializedName("PS_RESERVOIR")
    private String psReservoir;
    @SerializedName("PV_CONFIG")
    private String[] pvConfig;
    @SerializedName("PWRCFG_PEAK_PV_POWER")
    private String pwrcfgPeakPvPower;
    @SerializedName("SENEC_METER_SN")
    private String senecMeterSn;
    @SerializedName("SETUP_ABS_POWER")
    private String setupAbsPower;
    @SerializedName("SETUP_AGBS_ACCEPTED")
    private String setupAgbsAccepted;
    @SerializedName("SETUP_HV_PHASE")
    private String setupHvPhase;
    @SerializedName("SETUP_NUMBER_WALLBOXES")
    private String setupNumberWallboxes;
    @SerializedName("SETUP_PM_GRID_ADR")
    private String setupPmGridAdr;
    @SerializedName("SETUP_PM_HOUSE_ADR")
    private String setupPmHouseAdr;
    @SerializedName("SETUP_POWER_RULE")
    private String setupPowerRule;
    @SerializedName("SETUP_PV_INV_IP0")
    private String setupPvInvIp0;
    @SerializedName("SETUP_PV_INV_IP1")
    private String setupPvInvIp1;
    @SerializedName("SETUP_PV_INV_IP2")
    private String setupPvInvIp2;
    @SerializedName("SETUP_PV_INV_IP3")
    private String setupPvInvIp3;
    @SerializedName("SETUP_PV_INV_IP4")
    private String setupPvInvIp4;
    @SerializedName("SETUP_PV_INV_IP5")
    private String setupPvInvIp5;
    @SerializedName("SETUP_RCR_STEPS")
    private String[] setupRcrSteps;
    @SerializedName("SETUP_USED_PHASE")
    private String setupUsedPhase;
    @SerializedName("SETUP_USE_ABS_POWER")
    private String setupUseAbsPower;
    @SerializedName("SETUP_USE_DRM0")
    private String setupUseDrm0;
    @SerializedName("SETUP_USE_RCR")
    private String setupUseRcr;
    @SerializedName("SETUP_WALLBOX_SERIAL0")
    private String setupWallboxSerial0;
    @SerializedName("SETUP_WALLBOX_SERIAL1")
    private String setupWallboxSerial1;
    @SerializedName("SETUP_WALLBOX_SERIAL2")
    private String setupWallboxSerial2;
    @SerializedName("SETUP_WALLBOX_SERIAL3")
    private String setupWallbox_serial3;
    @SerializedName("SG_READY_CURR_MODE")
    private String sgReadyCurrMode;
    @SerializedName("SG_READY_ENABLED")
    private String sgReadyEnabled;
    @SerializedName("SG_READY_ENABLE_OVERWRITE")
    private String sg_readyEnableOverwrite;
    @SerializedName("SG_READY_EN_MODE1")
    private String sgReadyEnMode1;
    @SerializedName("SG_READY_OVERWRITE_RELAY")
    private String[] sgReadyOverwriteRelay;
    @SerializedName("SG_READY_POWER_COMM")
    private String sgReadyPowerComm;
    @SerializedName("SG_READY_POWER_PROP")
    private String sgReadyPowerProp;
    @SerializedName("SG_READY_TIME")
    private String sgReadyTime;
    @SerializedName("TEST_EG_METER")
    private String testEgMeter;
    @SerializedName("TEST_GENERATION_METER")
    private String testGenerationMeter;
    @SerializedName("TEST_HEATPUMP_METER")
    private String testHeatpumpMeter;
    @SerializedName("TEST_SENEC_METER")
    private String testSenecMeter;
    @SerializedName("ZEROMODULE")
    private String zeromodule;
}
