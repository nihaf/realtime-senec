package de.codematrosen.rts.model;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SystemUpdate {

    @SerializedName("FSM_STATE")
    private long fsm_state;
    @SerializedName("MISC")
    private long[] misc;
    @SerializedName("NPU_IMAGE_VERSION")
    private long npuImageVersion;
    @SerializedName("NPU_VER")
    private long npuVer;
    @SerializedName("UPDATE_AVAILABLE")
    private boolean updateAvailable;
    @SerializedName("USER_REBOOT_DEVICE")
    private boolean userRebootDevice;
    @SerializedName("USER_REQ_UPDATE")
    private boolean userRequireUpdate;
}
