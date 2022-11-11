package de.codematrosen.rts.model.log;

import androidx.annotation.Nullable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogEntry {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
    private final LocalDateTime dateTime;
    private final String level;
    private final String category;
    private final String message;

    public LogEntry(String dateTime, @Nullable String level, @Nullable String category, String message) {
        this.dateTime = LocalDateTime.parse(dateTime, FORMATTER);
        this.level = level;
        this.category = category;
        this.message = message;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getLevel() {
        return level;
    }

    public String getCategory() {
        return category;
    }

    public String getMessage() {
        return message;
    }
}
