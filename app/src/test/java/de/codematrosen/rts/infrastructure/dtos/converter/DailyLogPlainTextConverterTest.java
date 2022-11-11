package de.codematrosen.rts.infrastructure.dtos.converter;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import java.util.Collections;

import de.codematrosen.rts.model.log.DailyLogs;

public class DailyLogPlainTextConverterTest {

    @Test
    public void testConvertHavingAllLogEntryFields() {
        String dateTime = "2022-01-31 11:12:13";
        String level = "I";
        String category = "WALLBOX";
        String message = "1: charging with phase currents I_L1 = 0.00A, I_L2 = 0.00A";
        String logLine = dateTime + " [" + level + "|" + category + "] " + message;

        DailyLogs result = new DailyLogPlainTextConverter().convert(Collections.singletonList(logLine));

        assertThat(result).isNotNull();
        assertThat(result.getLogEntries())
                .singleElement()
                .satisfies(logEntry -> assertThat(logEntry.getDateTime()).isNotNull())
                .satisfies(logEntry -> assertThat(logEntry.getLevel()).isEqualTo(level))
                .satisfies(logEntry -> assertThat(logEntry.getCategory()).isEqualTo(category))
                .satisfies(logEntry -> assertThat(logEntry.getMessage()).isEqualTo(message));
    }

    @Test
    public void testConvertWithoutLevelAndCategoryLogEntryFields() {
        String dateTime = "2022-01-31 11:12:13";
        String message = "1: charging with phase currents I_L1 = 0.00A, I_L2 = 0.00A";
        String logLineWithoutLevelCategory = dateTime + " " + message;

        DailyLogs result = new DailyLogPlainTextConverter().convert(Collections.singletonList(logLineWithoutLevelCategory));

        assertThat(result).isNotNull();
        assertThat(result.getLogEntries())
                .singleElement()
                .satisfies(logEntry -> assertThat(logEntry.getDateTime()).isNotNull())
                .satisfies(logEntry -> assertThat(logEntry.getLevel()).isNull())
                .satisfies(logEntry -> assertThat(logEntry.getCategory()).isNull())
                .satisfies(logEntry -> assertThat(logEntry.getMessage()).isEqualTo(message));
    }
}