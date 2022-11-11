package de.codematrosen.rts.model.log;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.Test;

import java.time.DateTimeException;

public class LogEntryTest {

    @Test
    public void testConstructorWithCorrectDateTimeFormat() {
        assertThatNoException().isThrownBy(() -> new LogEntry("2022-01-31 01:02:03", "I", "BMS", "message"));
    }

    @Test
    public void testConstructorWithWrongDateTimeFormat() {
        assertThatThrownBy(() -> new LogEntry("2022-01-31 1:2:30", "I", "BMS", "message"))
                .isInstanceOf(DateTimeException.class);
    }
}