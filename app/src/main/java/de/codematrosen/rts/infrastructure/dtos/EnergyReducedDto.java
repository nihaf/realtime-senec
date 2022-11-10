package de.codematrosen.rts.infrastructure.dtos;

import com.google.gson.annotations.SerializedName;

public class EnergyReducedDto {

    @SerializedName("GUI_BAT_DATA_FUEL_CHARGE")
    private String guiBatDataFuelCharge;
    @SerializedName("GUI_BAT_DATA_POWER")
    private String guiBatDataPower;
    @SerializedName("GUI_BAT_DATA_CURRENT")
    private String guiBatDataCurrent;
    @SerializedName("GUI_BAT_DATA_VOLTAGE")
    private String guiBatDataVoltage;
    @SerializedName("GUI_BOOSTING_INFO")
    private String guiBoostingInfo;
    @SerializedName("GUI_CHARGING_INFO")
    private String guiChargingInfo;
    @SerializedName("GUI_GRID_POW")
    private String guiGridPow;
    @SerializedName("GUI_HOUSE_POW")
    private String guiHousePow;
    @SerializedName("GUI_INVERTER_POWER")
    private String guiInverterPower;
    @SerializedName("STAT_STATE")
    private String statState;

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

    public String getGuiBatDataCurrent() {
        return guiBatDataCurrent;
    }

    public void setGuiBatDataCurrent(String guiBatDataCurrent) {
        this.guiBatDataCurrent = guiBatDataCurrent;
    }

    public String getGuiBatDataVoltage() {
        return guiBatDataVoltage;
    }

    public void setGuiBatDataVoltage(String guiBatDataVoltage) {
        this.guiBatDataVoltage = guiBatDataVoltage;
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