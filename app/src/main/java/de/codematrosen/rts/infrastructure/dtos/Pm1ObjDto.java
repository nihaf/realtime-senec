package de.codematrosen.rts.infrastructure.dtos;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

public class Pm1ObjDto {

    //{"PM1OBJ1":{"FREQ":"fl_424800A4","U_AC":["fl_4366DD0D","fl_43673966","fl_43678128"],"I_AC":["fl_3FC7529D","fl_3FBE5138","fl_3F0B9583"],"P_AC":["fl_C3AC5A4F","fl_4399A457","fl_425E18C1"],"P_TOTAL":"fl_4190D202"},"PM1OBJ2":{"FREQ":"fl_42480CBD","U_AC":["fl_4366C148","fl_4367322E","fl_436752A3"],"I_AC":["fl_3EA4D2B5","fl_3EAED0AF","fl_3EA80351"],"P_AC":["fl_00400000","fl_00400000","fl_00400000"],"P_TOTAL":"fl_00C00000"},"ENERGY":{"STAT_STATE":"u8_12","STAT_HOURS_OF_OPERATION":"u3_00003A8D","GUI_BAT_DATA_POWER":"fl_C3E304D7","GUI_BAT_DATA_VOLTAGE":"fl_424C0F5C","GUI_BAT_DATA_CURRENT":"fl_C10E6666","GUI_BAT_DATA_FUEL_CHARGE":"fl_4236FA8E","GUI_HOUSE_POW":"fl_43EC11F7","GUI_INVERTER_POWER":"fl_80000000","STAT_LIMITED_NET_SKEW":"u8_00"},"SYS_UPDATE":{"NPU_VER":"u1_000A","NPU_IMAGE_VERSION":"u1_0906"}}

    @SerializedName("P_TOTAL")
    private String totalPower;

    public String getTotalPower() {
        return totalPower;
    }

    public void setTotalPower(String totalPower) {
        this.totalPower = totalPower;
    }
}