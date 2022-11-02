package de.codematrosen.rts.infrastructure.dtos;

import com.google.gson.annotations.SerializedName;

public class SenecEnergyResponseDto {
    @SerializedName("ENERGY")
    private EnergyReducedDto energy;

    public EnergyReducedDto getEnergy() {
        return energy;
    }

    public void setEnergy(EnergyReducedDto energy) {
        this.energy = energy;
    }
}
