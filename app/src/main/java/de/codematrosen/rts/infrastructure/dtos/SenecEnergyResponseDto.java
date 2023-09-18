package de.codematrosen.rts.infrastructure.dtos;

import com.google.gson.annotations.SerializedName;

public class SenecEnergyResponseDto {
    @SerializedName("ENERGY")
    private EnergyReducedDto energy;

    @SerializedName("WALLBOX")
    private WallboxReducedDto wallbox;

    public EnergyReducedDto getEnergy() {
        return energy;
    }

    public void setEnergy(EnergyReducedDto energy) {
        this.energy = energy;
    }

    public WallboxReducedDto getWallbox() {
        return wallbox;
    }

    public void setWallbox(WallboxReducedDto wallbox) {
        this.wallbox = wallbox;
    }
}
