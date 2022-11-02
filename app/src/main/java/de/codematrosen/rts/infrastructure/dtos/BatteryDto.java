package de.codematrosen.rts.infrastructure.dtos;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BatteryDto {

    @SerializedName("CEI_LIMIT")
    private String ceiLimit;
    @SerializedName("DRM0_ASSERT")
    private String drm0Assert;
    @SerializedName("ISLAND_ENABLE")
    private String islandEnable;
    @SerializedName("NSP2_FW")
    private String nsp2Fw;
    @SerializedName("NSP_FW")
    private String nspFw;
    @SerializedName("RESET")
    private String reset;
    @SerializedName("SELFTEST_ACT")
    private String[] selfTestAct;
    @SerializedName("SELFTEST_LIMIT")
    private String[] selfTestLimit;
    @SerializedName("SELFTEST_OFF")
    private String[] selfTestOff;
    @SerializedName("SELFTEST_STATE")
    private String[] selfTestState;
    @SerializedName("SELFTEST_STEP")
    private String[] selfTestStep;
     @SerializedName("SELFTEST_TIME")
    private String[] selfTestTime;
    @SerializedName("SELFTEST_OVERALL_STATE")
    private String selfTestOverallState;
    @SerializedName("SERIAL")
    private String serial;
    @SerializedName("SPARE_CAPACITY")
    private String spareCapacity;
    @SerializedName("TRIG_ITALY_SELF")
    private String trigItalySelf;
    @SerializedName("TYPE")
    private String type;
}