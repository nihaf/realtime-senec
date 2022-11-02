package de.codematrosen.rts.infrastructure.dtos;

import com.google.gson.annotations.SerializedName;

public class EnergyDto {

    @SerializedName("CAPTESTMODULE")
    private String[] capTestModule;
    @SerializedName("GUI_BAT_DATA_COLLECTED")
    private String guiBatDataCollected;
    @SerializedName("GUI_BAT_DATA_CURRENT")
    private String guiBatDataCurrent;
    @SerializedName("GUI_BAT_DATA_FUEL_CHARGE")
    private String guiBatDataFuelCharge;
    @SerializedName("GUI_BAT_DATA_MAX_CELL_VOLTAGE")
    private String guiBatDataMaxCellVoltage;
    @SerializedName("GUI_BAT_DATA_MIN_CELL_VOLTAGE")
    private String guiBatDataMinCellVoltage;
    @SerializedName("GUI_BAT_DATA_POWER")
    private String guiBatDataPower;
    @SerializedName("GUI_BAT_DATA_VOLTAGE")
    private String guiBatDataVoltage;
    @SerializedName("GUI_BOOSTING_INFO")
    private String guiBoostingInfo;
    @SerializedName("GUI_CAP_TEST_STATE")
    private String guiCapTestState;
    @SerializedName("GUI_CHARGING_INFO")
    private String guiChargingInfo;
    @SerializedName("GUI_GRID_POW")
    private String guiGridPow;
    @SerializedName("GUI_HOUSE_POW")
    private String guiHousePow;
    @SerializedName("GUI_INIT_CHARGE_START")
    private String guiInitChargeStart;
    @SerializedName("GUI_INIT_CHARGE_STOP")
    private String guiInitChargeStop;
    @SerializedName("GUI_INVERTER_POWER")
    private String guiInverterPower;
    @SerializedName("GUI_TEST_CHARGE_STAT")
    private String guiTestChargeStat;
    @SerializedName("GUI_TEST_DISCHARGE_STAT")
    private String guiTestDischargeStat;
    @SerializedName("INIT_CHARGE_ACK")
    private String initChargeAck;
    @SerializedName("INIT_CHARGE_DIFF_VOLTAGE")
    private String initChargeDiffVoltage;
    @SerializedName("INIT_CHARGE_MAX_CURRENT")
    private String initChargeMaxCurrent;
    @SerializedName("INIT_CHARGE_MAX_VOLTAGE")
    private String init_chargeMaxVoltage;
    @SerializedName("INIT_CHARGE_MIN_VOLTAGE")
    private String initChargeMinVoltage;
    @SerializedName("INIT_CHARGE_RERUN")
    private String initChargeRerun;
    @SerializedName("INIT_CHARGE_RUNNING")
    private String initChargeRunning;
    @SerializedName("INIT_CHARGE_STATE")
    private String initChargeState;
    @SerializedName("INIT_CHARGE_TIMER")
    private String initChargeTimer;
    @SerializedName("INIT_DISCHARGE_MAX_CURRENT")
    private String initDischargeMaxCurrent;
    @SerializedName("LI_STORAGE_MODE_RUNNING")
    private String liStorageModeRunning;
    @SerializedName("LI_STORAGE_MODE_START")
    private String liStorageModeStart;
    @SerializedName("LI_STORAGE_MODE_STOP")
    private String liStorageModeStop;
    @SerializedName("OFFPEAK_DURATION")
    private String offpeakDuration;
    @SerializedName("OFFPEAK_POWER")
    private String offpeakPower;
    @SerializedName("OFFPEAK_RUNNING")
    private String offpeakRunning;
    @SerializedName("OFFPEAK_TARGET")
    private String offpeakTarget;
    @SerializedName("SAFE_CHARGE_FORCE")
    private String safeChargeForce;
    @SerializedName("SAFE_CHARGE_PROHIBIT")
    private String safeChargeProhibit;
    @SerializedName("SAFE_CHARGE_RUNNING")
    private String safeChargeRunning;
    @SerializedName("STAT_HOURS_OF_OPERATION")
    private String statHoursOfOperation;
    @SerializedName("STAT_LIMITED_NET_SKEW")
    private String statLimitedNetSkew;
    @SerializedName("STAT_STATE")
    private String statState;
    @SerializedName("ZERO_EXPORT")
    private String zeroExport;

    public String getGuiBatDataFuelCharge() {
        return guiBatDataFuelCharge;
    }

    public void setGuiBatDataFuelCharge(String guiBatDataFuelCharge) {
        this.guiBatDataFuelCharge = guiBatDataFuelCharge;
    }

    public String getGuiBatDataPower() {
        return guiBatDataPower;
    }

    public void setGuiBatDataPower(String guiBatDataPower) {
        this.guiBatDataPower = guiBatDataPower;
    }

    public String getGuiBoostingInfo() {
        return guiBoostingInfo;
    }

    public void setGuiBoostingInfo(String guiBoostingInfo) {
        this.guiBoostingInfo = guiBoostingInfo;
    }

    public String getGuiChargingInfo() {
        return guiChargingInfo;
    }

    public void setGuiChargingInfo(String guiChargingInfo) {
        this.guiChargingInfo = guiChargingInfo;
    }

    public String getGuiGridPow() {
        return guiGridPow;
    }

    public void setGuiGridPow(String guiGridPow) {
        this.guiGridPow = guiGridPow;
    }

    public String getGuiHousePow() {
        return guiHousePow;
    }

    public void setGuiHousePow(String guiHousePow) {
        this.guiHousePow = guiHousePow;
    }

    public String getGuiInverterPower() {
        return guiInverterPower;
    }

    public void setGuiInverterPower(String guiInverterPower) {
        this.guiInverterPower = guiInverterPower;
    }

    public String getStatState() {
        return statState;
    }

    public void setStatState(String statState) {
        this.statState = statState;
    }
}