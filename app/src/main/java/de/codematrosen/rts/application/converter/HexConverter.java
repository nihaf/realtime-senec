package de.codematrosen.rts.application.converter;

import android.util.Log;

public class HexConverter {

    private static final String TAG = HexConverter.class.getSimpleName();

    private HexConverter() {
        // hide constructor
    }

    public static String convert(String inputToConvert) {
        String[] tmp = inputToConvert.split("_");
        try {
            String prefix = tmp[0];
            String value = tmp[1];
            switch (prefix) {
                case "fl":
                    return String.valueOf(hexToFloat(value));
                case "u1":
                case "u3":
                case "u6":
                case "u8":
                case "i1":
                case "i3":
                case "i8":
                    return String.valueOf(hexToLong(value));
                case "ch":
                case "st":
                    return value;
                case "er":
                    Log.e(TAG, "Error value: " + value);
                    break;
                default:
                    Log.e(TAG, "error: unknown variable type: " + value);
                    break;
            }
        } catch (Exception e) {
            Log.e(TAG, "Failed to convert: " + inputToConvert);
        }
        return "failed";
    }

    public static float hexToFloat(String hexString) {
        return Float.intBitsToFloat(hexToLong(hexString).intValue());
    }

    public static Long hexToLong(String hexString) {
        return Long.parseLong(hexString, 16);
    }
}
