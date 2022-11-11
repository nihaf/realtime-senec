package de.codematrosen.rts.infrastructure.dtos.converter;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import de.codematrosen.rts.model.log.DailyLogs;
import de.codematrosen.rts.model.log.LogEntry;
import retrofit2.Converter;

public class DailyLogPlainTextConverter implements Converter<List<String>, DailyLogs> {

    private static final Pattern PATTERN = Pattern.compile("^(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2})\\s?(\\[(\\w)\\|(.+)])?\\s?(.*)$");

    @Override
    public DailyLogs convert(List<String> value) {
        return new DailyLogs(value.stream()
                .map(PATTERN::matcher)
                .filter(Matcher::matches)
                .map(matcher -> new LogEntry(matcher.group(1), matcher.group(3), matcher.group(4), matcher.group(5)))
                .collect(Collectors.toList()));
    }
}
