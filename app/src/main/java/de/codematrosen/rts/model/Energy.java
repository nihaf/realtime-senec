package de.codematrosen.rts.model;

public class Energy {

    private Float[] capTestModule;
    private Integer guiBatDataCollected;
    private Float guiBatDataCurrent;
    private Float guiBatDataFuelCharge;
    private Integer guiBatDataMaxCellVoltage;
    private Integer guiBatDataMinCellVoltage;
    private Float guiBatDataPower;
    private Float guiBatDataVoltage;
    private Boolean guiBoostingInfo;
    private Integer guiCapTestState;
    private Boolean guiChargingInfo;
    private Float guiGridPow;
    private Float guiHousePow;
    private Boolean guiInitChargeStart;
    private Boolean guiInitChargeStop;
    private Float guiInverterPower;
    private Integer guiTestChargeStat;
    private Integer guiTestDischargeStat;
    private Boolean initChargeAck;
    private Float initChargeDiffVoltage;
    private Float initChargeMaxCurrent;
    private Float init_chargeMaxVoltage;
    private Float initChargeMinVoltage;
    private Integer initChargeRerun;
    private Integer initChargeRunning;
    private Integer initChargeState;
    private Integer initChargeTimer;
    private Float initDischargeMaxCurrent;
    private Boolean liStorageModeRunning;
    private Boolean liStorageModeStart;
    private Boolean liStorageModeStop;
    private Integer offpeakDuration;
    private Float offpeakPower;
    private Integer offpeakRunning;
    private Integer offpeakTarget;
    private Boolean safeChargeForce;
    private Boolean safeChargeProhibit;
    private Boolean safeChargeRunning;
    private Integer statHoursOfOperation;
    private Boolean statLimitedNetSkew;
    private Integer statState;
    private Boolean zeroExport;

    public Float getGuiBatDataFuelCharge() {
        return guiBatDataFuelCharge;
    }

    public void setGuiBatDataFuelCharge(Float guiBatDataFuelCharge) {
        this.guiBatDataFuelCharge = guiBatDataFuelCharge;
    }

    public Float getGuiBatDataPower() {
        return guiBatDataPower;
    }

    public void setGuiBatDataPower(Float guiBatDataPower) {
        this.guiBatDataPower = guiBatDataPower;
    }

    public Boolean getGuiBoostingInfo() {
        return guiBoostingInfo;
    }

    public void setGuiBoostingInfo(Boolean guiBoostingInfo) {
        this.guiBoostingInfo = guiBoostingInfo;
    }

    public Boolean getGuiChargingInfo() {
        return guiChargingInfo;
    }

    public void setGuiChargingInfo(Boolean guiChargingInfo) {
        this.guiChargingInfo = guiChargingInfo;
    }

    public Float getGuiGridPow() {
        return guiGridPow;
    }

    public void setGuiGridPow(Float guiGridPow) {
        this.guiGridPow = guiGridPow;
    }

    public Float getGuiHousePow() {
        return guiHousePow;
    }

    public void setGuiHousePow(Float guiHousePow) {
        this.guiHousePow = guiHousePow;
    }

    public Float getGuiInverterPower() {
        return guiInverterPower;
    }

    public void setGuiInverterPower(Float guiInverterPower) {
        this.guiInverterPower = guiInverterPower;
    }

    public Integer getStatState() {
        return statState;
    }

    public void setStatState(Integer statState) {
        this.statState = statState;
    }
}