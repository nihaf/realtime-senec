package de.codematrosen.rts.model;

import com.google.gson.annotations.SerializedName;

public class Wallbox {

    private Float apparentChargingPower;
    private Float l1ChargingCurrent;
    private Float l2ChargingCurrent;
    private Float l3ChargingCurrent;
    private String stateId;

    public Float getApparentChargingPower() {
        return apparentChargingPower;
    }

    public void setApparentChargingPower(Float apparentChargingPower) {
        this.apparentChargingPower = apparentChargingPower;
    }

    public Float getL1ChargingCurrent() {
        return l1ChargingCurrent;
    }

    public void setL1ChargingCurrent(Float l1ChargingCurrent) {
        this.l1ChargingCurrent = l1ChargingCurrent;
    }

    public Float getL2ChargingCurrent() {
        return l2ChargingCurrent;
    }

    public void setL2ChargingCurrent(Float l2ChargingCurrent) {
        this.l2ChargingCurrent = l2ChargingCurrent;
    }

    public Float getL3ChargingCurrent() {
        return l3ChargingCurrent;
    }

    public void setL3ChargingCurrent(Float l3ChargingCurrent) {
        this.l3ChargingCurrent = l3ChargingCurrent;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }
}