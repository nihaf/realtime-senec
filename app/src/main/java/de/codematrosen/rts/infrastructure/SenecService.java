package de.codematrosen.rts.infrastructure;

import java.util.List;

import de.codematrosen.rts.infrastructure.dtos.SenecEnergyRequestDto;
import de.codematrosen.rts.infrastructure.dtos.SenecEnergyResponseDto;
import de.codematrosen.rts.infrastructure.dtos.SenecSolarRequestDto;
import de.codematrosen.rts.infrastructure.dtos.SenecSolarResponseDto;
import de.codematrosen.rts.infrastructure.dtos.SenecSystemInfoRequestDto;
import de.codematrosen.rts.infrastructure.dtos.SenecSystemInfoResponseDto;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SenecService {

    @GET("/log/{year}/{month}/{day}.log")
    Call<List<String>> getLogs(@Path("year") String year, @Path("month") String month, @Path("day") String day);

    @POST("/lala.cgi")
    Call<SenecEnergyResponseDto> getEnergyData(@Body SenecEnergyRequestDto request);

    @POST("/lala.cgi")
    Call<SenecSolarResponseDto> getSolarData(@Body SenecSolarRequestDto request);

    @POST("/lala.cgi")
    Call<SenecSystemInfoResponseDto> getSystemInfo(@Body SenecSystemInfoRequestDto request);
}
