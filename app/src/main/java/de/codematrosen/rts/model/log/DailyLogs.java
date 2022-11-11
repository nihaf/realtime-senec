package de.codematrosen.rts.model.log;

import java.util.ArrayList;
import java.util.List;

public class DailyLogs {
    private final List<LogEntry> logEntries;

    public DailyLogs() {
        logEntries = new ArrayList<>();
    }

    public DailyLogs(List<LogEntry> logEntries) {
        this.logEntries = logEntries;
    }

    public List<LogEntry> getLogEntries() {
        return logEntries;
    }
}
