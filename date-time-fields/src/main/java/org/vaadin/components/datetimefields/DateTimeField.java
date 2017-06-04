package org.vaadin.components.datetimefields;

import java.util.Date;
import java.util.Locale;

import org.joda.time.DateTime;

import com.vaadin.data.Property;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.ui.DateField;

public class DateTimeField extends DateField {

    public static class DateTimeConverter implements Converter<Date, DateTime> {

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

    public DateTimeField() {
        super();
        setConverter(new DateTimeConverter());
    }

    public DateTimeField(Property<DateTime> dataSource) throws IllegalArgumentException {
        super(dataSource);
        setConverter(new DateTimeConverter());
    }

    public DateTimeField(String caption, Property<DateTime> dataSource) {
        super(caption, dataSource);
        setConverter(new DateTimeConverter());
    }

    public DateTimeField(String caption) {
        super(caption);
        setConverter(new DateTimeConverter());
    }

}
