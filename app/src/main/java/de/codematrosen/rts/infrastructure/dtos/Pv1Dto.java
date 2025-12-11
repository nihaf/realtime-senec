package de.codematrosen.rts.infrastructure.dtos;

import com.google.gson.annotations.SerializedName;

/**
 * DTO for PV1 (internal PV inverter) data from SENEC backend.
 * Contains MPP power values for each string of the internal inverter.
 */
public class Pv1Dto {

    /**
     * MPP Power values array - contains power values for each MPP string.
     * Format: ["fl_...", "fl_...", "fl_..."] representing PV1MPP_POWER0, PV1MPP_POWER1, PV1MPP_POWER2
     */
    @SerializedName("MPP_POWER")
    private String[] mppPower;

    /**
     * Version info for the PV inverter.
     * Contains a version string like "HMI: 3.17.94 PU: 4.6.339 BDC: 5.5.10"
     */
    @SerializedName("VERSION")
    private String version;

    public String[] getMppPower() {
        return mppPower;
    }

    public void setMppPower(String[] mppPower) {
        this.mppPower = mppPower;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
