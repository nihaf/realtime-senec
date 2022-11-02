package de.codematrosen.rts.infrastructure.dtos;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// STECA is a inverter manufacturer
public class StecaDto {
    @SerializedName("BAT")
    private String BAT; //: "u8_01",
    @SerializedName("BDC_STATE")
    private String BDC_STATE; //: ["u3_00040000","u3_00000000","u3_40000000"],
    @SerializedName("ERROR")
    private String ERROR; //: "u8_00",
    @SerializedName("ERRORTEXT")
    private String ERRORTEXT; //: "st_",
    @SerializedName("ISLAND")
    private String ISLAND; //: "u8_00",
    @SerializedName("NUM_PV_CONFIG_POSSIBLE")
    private String NUM_PV_CONFIG_POSSIBLE; //: "u8_0A",
    @SerializedName("PV")
    private String PV; //: "u8_01",
    @SerializedName("PVSS")
    private String PVSS; //: "u8_04",
    @SerializedName("PV_CONFIG_POSSIBLE")
    private String PV_CONFIG_POSSIBLE; //: array long 8 [,
    @SerializedName("PV_INPUTS")
    private String PV_INPUTS; //: "u8_02",
    @SerializedName("RELAYS")
    private String RELAYS; //: "u3_0000000F",
    @SerializedName("STARTUP")
    private String STARTUP; //: "u1_0110",
    @SerializedName("STARTUP_ADD")
    private String STARTUP_ADD; //: "i3_FFFFFFFF"
}
