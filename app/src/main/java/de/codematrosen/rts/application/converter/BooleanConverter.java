package de.codematrosen.rts.application.converter;

public class BooleanConverter {

    public static boolean toBoolean(String numberString) {
        try {
            return Long.parseLong(numberString) == 1L;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
