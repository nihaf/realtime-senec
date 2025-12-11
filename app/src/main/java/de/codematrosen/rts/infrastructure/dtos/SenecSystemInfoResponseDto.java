package de.codematrosen.rts.infrastructure.dtos;

import com.google.gson.annotations.SerializedName;

/**
 * Response DTO for system information from SENEC backend.
 */
public class SenecSystemInfoResponseDto {

    @SerializedName("WIZARD")
    private WizardReducedDto wizard;

    @SerializedName("SYS_UPDATE")
    private SystemUpdateDto sysUpdate;

    @SerializedName("PV1")
    private Pv1Dto pv1;

    @SerializedName("BAT1")
    private BatteryDto battery;

    @SerializedName("ENERGY")
    private EnergyDto energy;

    public WizardReducedDto getWizard() {
        return wizard;
    }

    public void setWizard(WizardReducedDto wizard) {
        this.wizard = wizard;
    }

    public SystemUpdateDto getSysUpdate() {
        return sysUpdate;
    }

    public void setSysUpdate(SystemUpdateDto sysUpdate) {
        this.sysUpdate = sysUpdate;
    }

    public Pv1Dto getPv1() {
        return pv1;
    }

    public void setPv1(Pv1Dto pv1) {
        this.pv1 = pv1;
    }

    public BatteryDto getBattery() {
        return battery;
    }

    public void setBattery(BatteryDto battery) {
        this.battery = battery;
    }

    public EnergyDto getEnergy() {
        return energy;
    }

    public void setEnergy(EnergyDto energy) {
        this.energy = energy;
    }
}
