package de.codematrosen.rts.infrastructure.dtos.converter;

import de.codematrosen.rts.application.converter.HexConverter;
import de.codematrosen.rts.infrastructure.dtos.EnergyReducedDto;
import de.codematrosen.rts.infrastructure.dtos.Pm1ObjDto;
import de.codematrosen.rts.infrastructure.dtos.WallboxReducedDto;
import de.codematrosen.rts.model.Energy;
import de.codematrosen.rts.model.PowerMeter;
import de.codematrosen.rts.model.Wallbox;

public class EnergyDtoConverter {

    public static Energy fromDto(EnergyReducedDto dto) {
        Energy energy = new Energy();

        // Direct conversion without Optional chains - more efficient
        energy.setGuiBatDataPower(HexConverter.convertToFloat(dto.getGuiBatDataPower()));
        energy.setGuiBatDataFuelCharge(HexConverter.convertToFloat(dto.getGuiBatDataFuelCharge()));
        energy.setGuiBatDataVoltage(HexConverter.convertToFloat(dto.getGuiBatDataVoltage()));
        energy.setGuiBatDataCurrent(HexConverter.convertToFloat(dto.getGuiBatDataCurrent()));
        energy.setGuiBoostingInfo(HexConverter.convertToBoolean(dto.getGuiBoostingInfo()));
        energy.setGuiChargingInfo(HexConverter.convertToBoolean(dto.getGuiChargingInfo()));
        energy.setGuiGridPow(HexConverter.convertToFloat(dto.getGuiGridPow()));
        energy.setGuiHousePow(HexConverter.convertToFloat(dto.getGuiHousePow()));
        energy.setGuiInverterPower(HexConverter.convertToFloat(dto.getGuiInverterPower()));
        energy.setStatState(HexConverter.convertToInteger(dto.getStatState()));

        return energy;
    }

    public static PowerMeter fromDto(Pm1ObjDto dto) {
        // Direct conversion - more efficient than Optional chains
        Float totalPower = HexConverter.convertToFloat(dto.getTotalPower());
        return new PowerMeter(totalPower != null ? totalPower : 0.0f);
    }

    public static Wallbox fromDto(WallboxReducedDto dto) {
        Wallbox wallbox = new Wallbox();

        // Array bounds checking with direct conversion - more efficient
        String[] apparentPower = dto.getApparentChargingPower();
        if (apparentPower != null && apparentPower.length > 0) {
            wallbox.setApparentChargingPower(HexConverter.convertToFloat(apparentPower[0]));
        }

        String[] l1Current = dto.getL1ChargingCurrent();
        if (l1Current != null && l1Current.length > 0) {
            wallbox.setL1ChargingCurrent(HexConverter.convertToFloat(l1Current[0]));
        }

        String[] l2Current = dto.getL2ChargingCurrent();
        if (l2Current != null && l2Current.length > 0) {
            wallbox.setL2ChargingCurrent(HexConverter.convertToFloat(l2Current[0]));
        }

        String[] l3Current = dto.getL3ChargingCurrent();
        if (l3Current != null && l3Current.length > 0) {
            wallbox.setL3ChargingCurrent(HexConverter.convertToFloat(l3Current[0]));
        }

        String[] state = dto.getState();
        if (state != null && state.length > 0) {
            String convertedState = HexConverter.convertToString(state[0]);
            wallbox.setStateId(convertedState != null ? convertedState : "");
        }

        return wallbox;
    }
}
