package de.codematrosen.rts.application.converter;

import android.util.Log;

public class HexConverter {

    private static final String TAG = HexConverter.class.getSimpleName();

    private HexConverter() {
        // hide constructor
    }

    /**
     * Optimized string splitting that avoids regex compilation.
     * Splits on underscore character.
     */
    private static String[] splitOnUnderscore(String input) {
        if (input == null) return null;
        int underscoreIndex = input.indexOf('_');
        if (underscoreIndex == -1) return null;

        return new String[] {
            input.substring(0, underscoreIndex),
            input.substring(underscoreIndex + 1)
        };
    }

    /**
     * Legacy method - kept for backward compatibility.
     * Consider using type-specific methods (convertToFloat, convertToInteger, etc.) for better performance.
     */
    public static String convert(String inputToConvert) {
        String[] tmp = splitOnUnderscore(inputToConvert);
        if (tmp == null || tmp.length != 2) {
            Log.e(TAG, "Failed to convert: invalid format: " + inputToConvert);
            return null;
        }

        try {
            String prefix = tmp[0];
            String value = tmp[1];
            return switch (prefix) {
                case "fl" -> String.valueOf(hexToFloat(value));
                case "u1", "u3", "u6", "u8", "i1", "i3", "i8" -> String.valueOf(hexToLong(value));
                case "ch", "st" -> value;
                case "er" -> {
                    Log.e(TAG, "Error value: " + value);
                    yield null;
                }
                default -> {
                    Log.e(TAG, "error: unknown variable type: " + prefix);
                    yield null;
                }
            };
        } catch (Exception e) {
            Log.e(TAG, "Failed to convert: " + inputToConvert, e);
            return null;
        }
    }

    /**
     * Direct conversion to Float - eliminates intermediate String creation.
     * More efficient than convert() + Float.parseFloat()
     */
    public static Float convertToFloat(String inputToConvert) {
        if (inputToConvert == null) return null;

        String[] tmp = splitOnUnderscore(inputToConvert);
        if (tmp == null || tmp.length != 2) {
            Log.e(TAG, "Failed to convert to float: invalid format: " + inputToConvert);
            return null;
        }

        try {
            String prefix = tmp[0];
            String value = tmp[1];

            if ("fl".equals(prefix)) {
                return hexToFloat(value);
            } else if (prefix.startsWith("u") || prefix.startsWith("i")) {
                // For integer types, convert to float
                return hexToLong(value).floatValue();
            } else {
                Log.e(TAG, "Cannot convert type '" + prefix + "' to float: " + inputToConvert);
                return null;
            }
        } catch (NumberFormatException e) {
            Log.e(TAG, "Failed to convert to float: " + inputToConvert, e);
            return null;
        }
    }

    /**
     * Direct conversion to Integer - eliminates intermediate String creation.
     * More efficient than convert() + Integer.parseInt()
     */
    public static Integer convertToInteger(String inputToConvert) {
        if (inputToConvert == null) {
            return null;
        }

        String[] tmp = splitOnUnderscore(inputToConvert);
        if (tmp == null || tmp.length != 2) {
            Log.e(TAG, "Failed to convert to integer: invalid format: " + inputToConvert);
            return null;
        }

        try {
            String prefix = tmp[0];
            String value = tmp[1];

            if (prefix.startsWith("u") || prefix.startsWith("i")) {
                return hexToLong(value).intValue();
            } else {
                Log.e(TAG, "Cannot convert type '" + prefix + "' to integer: " + inputToConvert);
                return null;
            }
        } catch (NumberFormatException e) {
            Log.e(TAG, "Failed to convert to integer: " + inputToConvert, e);
            return null;
        }
    }

    /**
     * Direct conversion to Boolean - combines HexConverter + BooleanConverter logic.
     * More efficient than chaining convert() + BooleanConverter.toBoolean()
     */
    public static Boolean convertToBoolean(String inputToConvert) {
        if (inputToConvert == null) {
            return null;
        }

        String[] tmp = splitOnUnderscore(inputToConvert);
        if (tmp == null || tmp.length != 2) {
            Log.e(TAG, "Failed to convert to boolean: invalid format: " + inputToConvert);
            return null;
        }

        try {
            String prefix = tmp[0];
            String value = tmp[1];

            if (prefix.startsWith("u") || prefix.startsWith("i")) {
                return hexToLong(value) == 1L;
            } else {
                Log.e(TAG, "Cannot convert type '" + prefix + "' to boolean: " + inputToConvert);
                return null;
            }
        } catch (NumberFormatException e) {
            Log.e(TAG, "Failed to convert to boolean: " + inputToConvert, e);
            return null;
        }
    }

    /**
     * Direct conversion to String value - for string types (ch, st).
     */
    public static String convertToString(String inputToConvert) {
        if (inputToConvert == null) return null;

        String[] tmp = splitOnUnderscore(inputToConvert);
        if (tmp == null || tmp.length != 2) {
            Log.e(TAG, "Failed to convert to string: invalid format: " + inputToConvert);
            return null;
        }

        String prefix = tmp[0];
        String value = tmp[1];

        if ("ch".equals(prefix) || "st".equals(prefix)) {
            return value;
        } else {
            Log.e(TAG, "Cannot convert type '" + prefix + "' to string: " + inputToConvert);
            return null;
        }
    }

    public static float hexToFloat(String hexString) {
        return Float.intBitsToFloat(hexToLong(hexString).intValue());
    }

    public static Long hexToLong(String hexString) {
        return Long.parseLong(hexString, 16);
    }
}
