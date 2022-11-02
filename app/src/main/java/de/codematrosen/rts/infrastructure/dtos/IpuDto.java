package de.codematrosen.rts.infrastructure.dtos;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IpuDto {

    @SerializedName("VERSION")
    private String version;
}