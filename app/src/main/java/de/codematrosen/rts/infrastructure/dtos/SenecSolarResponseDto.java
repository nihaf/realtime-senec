package de.codematrosen.rts.infrastructure.dtos;

import com.google.gson.annotations.SerializedName;

/**
 * Response DTO for solar/PV inverter data from SENEC backend.
 */
public class SenecSolarResponseDto {
    
    @SerializedName("PV1")
    private Pv1Dto pv1;

    @SerializedName("PM1OBJ2")
    private Pm1ObjDto powerMeter2;

    public Pv1Dto getPv1() {
        return pv1;
    }

    public void setPv1(Pv1Dto pv1) {
        this.pv1 = pv1;
    }

    public Pm1ObjDto getPowerMeter2() {
        return powerMeter2;
    }

    public void setPowerMeter2(Pm1ObjDto powerMeter2) {
        this.powerMeter2 = powerMeter2;
    }
}
