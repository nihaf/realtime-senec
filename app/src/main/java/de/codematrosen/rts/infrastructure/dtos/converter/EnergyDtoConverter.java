package de.codematrosen.rts.infrastructure.dtos.converter;

import android.content.Context;

import java.util.Optional;

import de.codematrosen.rts.application.converter.BooleanConverter;
import de.codematrosen.rts.application.converter.HexConverter;
import de.codematrosen.rts.infrastructure.dtos.EnergyReducedDto;
import de.codematrosen.rts.infrastructure.dtos.WallboxReducedDto;
import de.codematrosen.rts.model.Energy;
import de.codematrosen.rts.model.Wallbox;

public class EnergyDtoConverter {

    public static Energy fromDto(EnergyReducedDto dto) {
        Energy energy = new Energy();
        energy.setGuiBatDataPower(Optional.ofNullable(dto.getGuiBatDataPower())
                .map(HexConverter::convert)
                .map(Float::parseFloat)
                .orElse(null));
        energy.setGuiBatDataFuelCharge(Optional.ofNullable(dto.getGuiBatDataFuelCharge())
                .map(HexConverter::convert)
                .map(Float::parseFloat)
                .orElse(null));
        energy.setGuiBatDataVoltage(Optional.ofNullable(dto.getGuiBatDataVoltage())
                .map(HexConverter::convert)
                .map(Float::parseFloat)
                .orElse(null));
        energy.setGuiBatDataCurrent(Optional.ofNullable(dto.getGuiBatDataCurrent())
                .map(HexConverter::convert)
                .map(Float::parseFloat)
                .orElse(null));
        energy.setGuiBoostingInfo(Optional.ofNullable(dto.getGuiBoostingInfo())
                .map(HexConverter::convert)
                .map(BooleanConverter::toBoolean)
                .orElse(null));
        energy.setGuiBoostingInfo(Optional.ofNullable(dto.getGuiBoostingInfo())
                .map(HexConverter::convert)
                .map(BooleanConverter::toBoolean)
                .orElse(null));
        energy.setGuiChargingInfo(Optional.ofNullable(dto.getGuiChargingInfo())
                .map(HexConverter::convert)
                .map(BooleanConverter::toBoolean)
                .orElse(null));
        energy.setGuiGridPow(Optional.ofNullable(dto.getGuiGridPow())
                .map(HexConverter::convert)
                .map(Float::parseFloat)
                .orElse(null));
        energy.setGuiHousePow(Optional.ofNullable(dto.getGuiHousePow())
                .map(HexConverter::convert)
                .map(Float::parseFloat)
                .orElse(null));
        energy.setGuiInverterPower(Optional.ofNullable(dto.getGuiInverterPower())
                .map(HexConverter::convert)
                .map(Float::parseFloat)
                .orElse(null));
        energy.setStatState(Optional.ofNullable(dto.getStatState())
                .map(HexConverter::convert)
                .map(Integer::parseInt)
                .orElse(null));
        return energy;
    }

    public static Wallbox fromDto(WallboxReducedDto dto) {
        Wallbox wallbox = new Wallbox();
        wallbox.setApparentChargingPower(Optional.ofNullable(dto.getApparentChargingPower()[0]).map(HexConverter::convert)
                .map(Float::parseFloat)
                .orElse(null));
        wallbox.setL1ChargingCurrent(Optional.ofNullable(dto.getL1ChargingCurrent()[0]).map(HexConverter::convert)
                .map(Float::parseFloat)
                .orElse(null));
        wallbox.setL2ChargingCurrent(Optional.ofNullable(dto.getL2ChargingCurrent()[0]).map(HexConverter::convert)
                .map(Float::parseFloat)
                .orElse(null));
        wallbox.setL3ChargingCurrent(Optional.ofNullable(dto.getL3ChargingCurrent()[0]).map(HexConverter::convert)
                .map(Float::parseFloat)
                .orElse(null));
        wallbox.setStateId(Optional.ofNullable(dto.getState()[0]).map(HexConverter::convert).orElse(""));
        return wallbox;
    }
}
