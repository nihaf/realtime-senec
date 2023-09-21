package de.codematrosen.rts.infrastructure.dtos;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeaturesDto {

    @SerializedName("CAR")
    private String car;
    @SerializedName("CLOUDREADY")
    private String cloudReady;
    @SerializedName("ECOGRIDREADY")
    private String ecoGridReady;
    @SerializedName("HEAT")
    private String heat;
    @SerializedName("ISLAND")
    private String island;
    @SerializedName("ISLAND_PRO")
    private String islandPro;
    @SerializedName("PEAKSHAVING")
    private String peakShaving;
    @SerializedName("SGREADY")
    private String sgReady;
    @SerializedName("SHKW")
    private String shkw;
    @SerializedName("SOCKET")
    private String socket;
}