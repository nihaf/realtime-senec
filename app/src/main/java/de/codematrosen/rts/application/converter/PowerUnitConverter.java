package de.codematrosen.rts.application.converter;

import java.text.DecimalFormat;

public class PowerUnitConverter {

    private static final String UNIT_WATT = " W";
    private static final String UNIT_KILOWATT = " kW";
    private static final String UNIT_MEGAWATT = " MW";
    private static final DecimalFormat FORMAT = new DecimalFormat("#.##");

    public static String convert(float watts) {
        if (watts >= 1000.0f && watts < 1000000.0f) {
            return FORMAT.format(watts / 1000.0f) + UNIT_KILOWATT;
        } else if (watts >= 1000000.0f) {
            return FORMAT.format(watts / 1000000.0f) + UNIT_MEGAWATT;
        }
        return FORMAT.format(watts) + UNIT_WATT;
    }
}
