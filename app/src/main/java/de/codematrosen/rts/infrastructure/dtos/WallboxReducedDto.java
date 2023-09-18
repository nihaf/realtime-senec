package de.codematrosen.rts.infrastructure.dtos;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WallboxReducedDto {

    @SerializedName("STATE")
    private String[] state;
    @SerializedName("APPARENT_CHARGING_POWER")
    private String[] apparentChargingPower;
    @SerializedName("L1_CHARGING_CURRENT")
    private String[] l1ChargingCurrent;
    @SerializedName("L2_CHARGING_CURRENT")
    private String[] l2ChargingCurrent;
    @SerializedName("L3_CHARGING_CURRENT")
    private String[] l3ChargingCurrent;

    public String[] getState() {
        return state;
    }

    public void setState(String[] state) {
        this.state = state;
    }

    public String[] getApparentChargingPower() {
        return apparentChargingPower;
    }

    public void setApparentChargingPower(String[] apparentChargingPower) {
        this.apparentChargingPower = apparentChargingPower;
    }

    public String[] getL1ChargingCurrent() {
        return l1ChargingCurrent;
    }

    public void setL1ChargingCurrent(String[] l1ChargingCurrent) {
        this.l1ChargingCurrent = l1ChargingCurrent;
    }

    public String[] getL2ChargingCurrent() {
        return l2ChargingCurrent;
    }

    public void setL2ChargingCurrent(String[] l2ChargingCurrent) {
        this.l2ChargingCurrent = l2ChargingCurrent;
    }

    public String[] getL3ChargingCurrent() {
        return l3ChargingCurrent;
    }

    public void setL3ChargingCurrent(String[] l3ChargingCurrent) {
        this.l3ChargingCurrent = l3ChargingCurrent;
    }

    @Override
    public String toString() {
        return "WallboxReducedDto{" +
                "state=" + Arrays.toString(state) +
                ", apparentChargingPower=" + Arrays.toString(apparentChargingPower) +
                ", l1ChargingCurrent=" + Arrays.toString(l1ChargingCurrent) +
                ", l2ChargingCurrent=" + Arrays.toString(l2ChargingCurrent) +
                ", l3ChargingCurrent=" + Arrays.toString(l3ChargingCurrent) +
                '}';
    }
}
