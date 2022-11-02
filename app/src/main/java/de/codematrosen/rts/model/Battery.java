import com.google.gson.annotations.SerializedName;

public class Battery {
    @SerializedName("CEI_LIMIT")
    private long ceiLimit;
    @SerializedName("DRM0_ASSERT")
    private boolean drm0Assert;
    @SerializedName("ISLAND_ENABLE")
    private boolean islandEnable;
    @SerializedName("NSP2_FW")
    private long nsp2Fw;
    @SerializedName("NSP_FW")
    private long nspFw;
    @SerializedName("RESET")
    private boolean reset;
    @SerializedName("SELFTEST_ACT")
    private float[] selfTestAct;
    @SerializedName("SELFTEST_LIMIT")
    private float[] selfTestLimit;
    @SerializedName("SELFTEST_OFF")
    private float[] selfTestOff;
    @SerializedName("SELFTEST_STATE")
    private long[] selfTestState;
    @SerializedName("SELFTEST_STEP")
    private String[] selfTestStep;
     @SerializedName("SELFTEST_TIME")
    private long[] selfTestTime;
    @SerializedName("SELFTEST_OVERALL_STATE")
    private long selfTestOVerallState;
    @SerializedName("SERIAL")
    private String serial;
    @SerializedName("SPARE_CAPACITY")
    private long spareCapacity;
    @SerializedName("TRIG_ITALY_SELF")
    private long trigItalySelf;
    @SerializedName("TYPE")
    private long type;
}
