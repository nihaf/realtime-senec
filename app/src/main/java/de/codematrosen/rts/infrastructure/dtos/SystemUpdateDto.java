package de.codematrosen.rts.infrastructure.dtos;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SystemUpdateDto {

    @SerializedName("FSM_STATE")
    private String fsm_state;
    @SerializedName("MISC")
    private String[] misc;
    @SerializedName("NPU_IMAGE_VERSION")
    private String npuImageVersion;
    @SerializedName("NPU_VER")
    private String npuVer;
    @SerializedName("UPDATE_AVAILABLE")
    private String updateAvailable;
    @SerializedName("USER_REBOOT_DEVICE")
    private String userRebootDevice;
    @SerializedName("USER_REQ_UPDATE")
    private String userRequireUpdate;
}
