package de.codematrosen.rts.model;

public class PowerMeter {

    private final Float totalPower;

    public PowerMeter(Float totalPower) {
        this.totalPower = totalPower;
    }

    public Float getTotalPower() {
        return totalPower;
    }
}