package org.vaadin.components.datetimefields;

import java.util.Locale;

import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;

import com.vaadin.data.Property;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.ui.TextField;

public class LocalTimeField extends TextField {

    private DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendHourOfDay(2).appendLiteral(":")
            .appendMinuteOfHour(2).toFormatter();

    public static class LocalTimeConverter implements Converter<String, LocalTime> {

        private final DateTimeFormatter formatter;

        public LocalTimeConverter(DateTimeFormatter formatter) {
            this.formatter = formatter;
        }

        @Override
        public Class<LocalTime> getModelType() {
            return LocalTime.class;
        }

        @Override
        public Class<String> getPresentationType() {
            return String.class;
        }

        @Override
        public LocalTime convertToModel(String value, Class<? extends LocalTime> targetType, Locale locale)
                throws com.vaadin.data.util.converter.Converter.ConversionException {
            if (value == null || value == "") {
                return null;
            }

            try {
                return LocalTime.parse(value, formatter);
            } catch (IllegalArgumentException e) {
                throw new ConversionException(e);
            }
        }

        @Override
        public String convertToPresentation(LocalTime value, Class<? extends String> targetType, Locale locale)
                throws com.vaadin.data.util.converter.Converter.ConversionException {
            return value == null ? null : value.toString(formatter);
        }

        public DateTimeFormatter getFormatter() {
            return formatter;
        }

    }

    @Override
    public void setConvertedValue(Object value) {
        super.setConvertedValue(value);
    }

    public LocalTimeField() {
        super();
        setConverter(new LocalTimeConverter(formatter));
        setNullRepresentation("");
    }

    public LocalTimeField(Property<LocalTime> dataSource) throws IllegalArgumentException {
        super(dataSource);
        setConverter(new LocalTimeConverter(formatter));
    }

    public LocalTimeField(String caption, Property<LocalTime> dataSource) {
        super(caption, dataSource);
        setConverter(new LocalTimeConverter(formatter));
    }

    public LocalTimeField(String caption) {
        super(caption);
        setConverter(new LocalTimeConverter(formatter));
    }

    public LocalTimeField(String caption, String value) {
        super(caption, value);
        setConverter(new LocalTimeConverter(formatter));
    }

    public DateTimeFormatter getFormatter() {
        return formatter;
    }

    public void setFormatter(DateTimeFormatter formatter) {
        this.formatter = formatter;
        setConverter(new LocalTimeConverter(this.formatter));
    }
}
