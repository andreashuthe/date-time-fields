package org.vaadin.components.datetimefields;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.joda.time.DateTime;

import com.vaadin.data.Property;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.ui.DateField;
import org.joda.time.DateTimeZone;
import org.vaadin.listener.DateTimeShortCutListener;

public class DateTimeField extends DateField implements IDateTimeField {

    private final DateTimeZone defaultTimeZone;

    public static class DateTimeConverter implements Converter<Date, DateTime> {

        private final DateTimeZone defaultTimeZone;

        public DateTimeConverter(final DateTimeZone defaultTimeZone){
            this.defaultTimeZone = defaultTimeZone;
        }

        @Override
        public DateTime convertToModel(Date value, Class<? extends DateTime> targetType, Locale locale)
                throws com.vaadin.data.util.converter.Converter.ConversionException {
            return value == null ? null : new DateTime(value.getTime());
        }

        @Override
        public Date convertToPresentation(DateTime value, Class<? extends Date> targetType, Locale locale)
                throws com.vaadin.data.util.converter.Converter.ConversionException {
            return value == null ? null : value.toDate();
        }

        @Override
        public Class<DateTime> getModelType() {
            return DateTime.class;
        }

        @Override
        public Class<Date> getPresentationType() {
            return Date.class;
        }

    }

    @Override
    public void addDateTimeShortCutListener(List<DateTimeShortCutListener> shortCutListeners) {
        for (final DateTimeShortCutListener shortCutListener : shortCutListeners) {
            this.addShortcutListener(shortCutListener);
        }
    }

    public DateTimeField() {
        this(DateTimeZone.UTC);
    }

    public DateTimeField(DateTimeZone dateTimeZone) {
        super();
        this.defaultTimeZone = dateTimeZone;
        setConverter(new DateTimeConverter(getDefaultTimeZone()));
    }

    public DateTimeField(Property<DateTime> dataSource, DateTimeZone dateTimeZone) throws IllegalArgumentException {
        super(dataSource);
        this.defaultTimeZone = dateTimeZone;
        setConverter(new DateTimeConverter(getDefaultTimeZone()));
    }

    public DateTimeField(String caption, Property<DateTime> dataSource, DateTimeZone dateTimeZone) {
        super(caption, dataSource);
        this.defaultTimeZone = dateTimeZone;
        setConverter(new DateTimeConverter(getDefaultTimeZone()));
    }

    public DateTimeField(String caption, DateTimeZone dateTimeZone) {
        super(caption);
        this.defaultTimeZone = dateTimeZone;
        setConverter(new DateTimeConverter(getDefaultTimeZone()));
    }

    public DateTimeZone getDefaultTimeZone() {
        return defaultTimeZone;
    }

    @Override
    public DateTime getConvertedValue() {
        return (DateTime) super.getConvertedValue();
    }

    @Override
    @Deprecated
    public Date getValue() {
        return super.getValue();
    }
}
