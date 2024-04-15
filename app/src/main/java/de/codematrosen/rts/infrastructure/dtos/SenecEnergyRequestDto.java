package de.codematrosen.rts.infrastructure.dtos;

import static java.util.Collections.singletonMap;

import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SenecEnergyRequestDto extends SenecRequestDto {
    @SerializedName("ENERGY")
    private final Map<String, Object> energy;

    @SerializedName("PM1OBJ2")
    private final Map<String, Object> powerMeterSma;

    @SerializedName("WALLBOX")
    private final Map<String, Object> wallbox;

    public SenecEnergyRequestDto() {
        energy = new HashMap<>();
        energy.put("STAT_STATE", "");
        energy.put("GUI_BAT_DATA_POWER", "");
        energy.put("GUI_INVERTER_POWER", "");
        energy.put("GUI_GRID_POW", "");
        energy.put("GUI_HOUSE_POW", "");
        energy.put("GUI_BAT_DATA_FUEL_CHARGE", "");
        energy.put("GUI_BAT_DATA_VOLTAGE", "");
        energy.put("GUI_BAT_DATA_CURRENT", "");
        energy.put("GUI_CHARGING_INFO", "");
        energy.put("GUI_BOOSTING_INFO", "");

        powerMeterSma = singletonMap("P_TOTAL", "");

        wallbox = new HashMap<>();
        wallbox.put("STATE", "");
        wallbox.put("APPARENT_CHARGING_POWER", "");
        wallbox.put("L1_CHARGING_CURRENT", "");
        wallbox.put("L2_CHARGING_CURRENT", "");
        wallbox.put("L3_CHARGING_CURRENT", "");
    }
}
