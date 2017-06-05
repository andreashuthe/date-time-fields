package org.vaadin.components.datetimefields.util;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by andreas_h on 05.06.17.
 */
public class ReversableDateTimeFormat {

    private static final String defaultPattern = "MM/dd/yyyy";

    private final Map<DateTimeFormatter, String> patternHistory;

    public ReversableDateTimeFormat() {
        patternHistory = new HashMap<DateTimeFormatter, String>();
    }

    public DateTimeFormatter forPattern(String pattern) {
        final DateTimeFormatter dateTimeFormatter = pattern == null ? DateTimeFormat.forPattern(defaultPattern) : DateTimeFormat.forPattern(pattern);
        patternHistory.put(dateTimeFormatter, pattern == null ? defaultPattern : pattern);
        return dateTimeFormatter;
    }

    public String getPattern(DateTimeFormatter dateTimeFormatter) {
        return patternHistory.get(dateTimeFormatter);
    }

}
