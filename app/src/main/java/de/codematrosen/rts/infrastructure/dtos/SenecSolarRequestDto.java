package de.codematrosen.rts.infrastructure.dtos;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

/**
 * Request DTO for fetching solar/PV inverter details from SENEC backend.
 * Requests MPP power values from internal PV inverters and power meter data.
 */
public class SenecSolarRequestDto extends SenecRequestDto {
    
    @SerializedName("PV1")
    private final Map<String, Object> pv1;

    @SerializedName("PM1OBJ2")
    private final Map<String, Object> powerMeter2;

    public SenecSolarRequestDto() {
        pv1 = new HashMap<>();
        // Internal MPP power values for each string
        pv1.put("MPP_POWER", "");  // Array of MPP power values

        powerMeter2 = new HashMap<>();
        powerMeter2.put("P_TOTAL", "");  // Total power from external power meter
    }
}
