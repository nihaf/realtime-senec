package de.codematrosen.rts.infrastructure.dtos;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class SenecEnergyRequestDto extends SenecRequestDto {
    @SerializedName("ENERGY")
    private final Map<String, Object> energy;

    public SenecEnergyRequestDto() {
        energy = new HashMap<>();
        energy.put("STAT_STATE", "");
        energy.put("GUI_BAT_DATA_POWER", "");
        energy.put("GUI_INVERTER_POWER", "");
        energy.put("GUI_GRID_POW", "");
        energy.put("GUI_HOUSE_POW", "");
        energy.put("GUI_BAT_DATA_FUEL_CHARGE", "");
        energy.put("GUI_CHARGING_INFO", "");
        energy.put("GUI_BOOSTING_INFO", "");
    }
}
