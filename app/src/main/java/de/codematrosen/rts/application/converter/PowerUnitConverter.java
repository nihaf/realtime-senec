package de.codematrosen.rts.application.converter;

import java.text.DecimalFormat;

import de.codematrosen.rts.R;

public class PowerUnitConverter {

    public static int getUnitId(float watts) {
        if (watts >= 1000.0f && watts < 1000000.0f) {
            return R.string.unit_kilowatt;
        } else if (watts >= 1000000.0f) {
            return R.string.unit_megawatt;
        }
        return R.string.unit_watt;
    }
}
