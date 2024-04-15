package de.codematrosen.rts.infrastructure.dtos;

import com.google.gson.annotations.SerializedName;

public class SenecEnergyResponseDto {
    @SerializedName("ENERGY")
    private EnergyReducedDto energy;

    @SerializedName("PM1OBJ2")
    private Pm1ObjDto powerMeter2;

    @SerializedName("WALLBOX")
    private WallboxReducedDto wallbox;

    public EnergyReducedDto getEnergy() {
        return energy;
    }

    public void setEnergy(EnergyReducedDto energy) {
        this.energy = energy;
    }

    public Pm1ObjDto getPowerMeter2() {
        return powerMeter2;
    }

    public void setPowerMeter2(Pm1ObjDto powerMeter2) {
        this.powerMeter2 = powerMeter2;
    }

    public WallboxReducedDto getWallbox() {
        return wallbox;
    }

    public void setWallbox(WallboxReducedDto wallbox) {
        this.wallbox = wallbox;
    }
}
