package org.vaadin.components.datetimefields;

import com.vaadin.data.Property;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.HorizontalLayout;
import org.apache.commons.collections.CollectionUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalTime;
import org.joda.time.MutableDateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.vaadin.components.datetimefields.util.DateTimeAndDefaultTimeEnum;
import org.vaadin.components.datetimefields.util.ReversableDateTimeFormat;
import org.vaadin.listener.DateTimeShortCutListener;
import org.vaadin.listener.util.DateTimeShortCutListenerUtil;

import java.util.Collection;
import java.util.List;
import java.util.Locale;

/**
 * Created by andreas_h on 05.06.17.
 */
public class DateTimeAndLocalTimeField extends CustomField<DateTime> implements IDateTimeField {

    private static final DateTimeZone innerDateTimeZone = DateTimeZone.UTC;

    private DateTimeFormatter timeFormatter = new DateTimeFormatterBuilder().appendHourOfDay(2).appendLiteral(":")
            .appendMinuteOfHour(2).toFormatter();

    private final ReversableDateTimeFormat reversableDateTimeFormat = new ReversableDateTimeFormat();

    private final DateTimeZone dateTimeZone;
    private final LocalTimeField localTimeField;

    private final DateTimeField dateTimeField;

    private final String defaultTime;
    private final Locale defaultLocale;

    private final DateTimeFormatter dateFormatter;

    public DateTimeAndLocalTimeField(final DateTimeZone dateTimeZone, final Locale locale, String dateFormat) {
        this(DateTimeAndDefaultTimeEnum.DEFAULT, dateTimeZone, locale, dateFormat);
    }

    public DateTimeAndLocalTimeField(final DateTimeAndDefaultTimeEnum defaultTime, final DateTimeZone dateTimeZone, final Locale locale, String dateFormat) {
        this(defaultTime.getTimeValue(), dateTimeZone, locale, dateFormat);
    }

    public DateTimeAndLocalTimeField(final String defaultTime, final DateTimeZone dateTimeZone, final Locale locale, String dateFormatPattern) {
        super();
        this.defaultTime = defaultTime;
        this.dateTimeZone = dateTimeZone;
        this.defaultLocale = locale;
        this.dateTimeField = new DateTimeField(dateTimeZone);
        this.dateFormatter = reversableDateTimeFormat.forPattern(dateFormatPattern == null ? dateTimeField.getDateFormat() : dateFormatPattern);
        this.localTimeField = new LocalTimeField();

        intiFields();
    }

    private void intiFields() {

        // Initialize our new UI component

        dateTimeField.addValueChangeListener(new DateValueChangeListener());

        dateTimeField.setImmediate(true);
        dateTimeField.setDateFormat(reversableDateTimeFormat.getPattern(getDateFormatter()));

    }

    @Override
    public void addDateTimeShortCutListener(List<DateTimeShortCutListener> shortCutListeners) {
        for (final DateTimeShortCutListener shortCutListener : shortCutListeners) {
            dateTimeField.addShortcutListener(shortCutListener);
        }
    }

    @Override
    protected Component initContent() {
        final HorizontalLayout layout = new HorizontalLayout();

        layout.addComponent(dateTimeField);
        layout.addComponent(localTimeField);

        return layout;
    }

    @Override
    public Class<? extends DateTime> getType() {
        return DateTime.class;
    }

    @Override
    public void setValue(DateTime newFieldValue)
            throws com.vaadin.data.Property.ReadOnlyException,
            Converter.ConversionException {
        super.setValue(newFieldValue);
    }

    @Override
    public DateTime getValue() {

        DateTime dateTime = (DateTime) dateTimeField.getConverter().convertToModel(dateTimeField.getValue(), DateTime.class, getDefaultLocale());

        final LocalTime localTime = (LocalTime) localTimeField.getConverter().convertToModel(localTimeField.getValue(), LocalTime.class, getDefaultLocale());

        if (localTime == null){
            return null;
        } else {
            dateTime = dateTime.withHourOfDay(localTime.getHourOfDay());
            dateTime = dateTime.withMinuteOfHour(localTime.getMinuteOfHour());
            final MutableDateTime dateTimeWithTimeZone = dateTime.toMutableDateTime(innerDateTimeZone);
            return dateTimeWithTimeZone.toDateTime();
        }
    }

    @Override
    public void setReadOnly(boolean readOnly) {
        super.setReadOnly(readOnly);
        dateTimeField.setReadOnly(readOnly);
        localTimeField.setReadOnly(readOnly);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        dateTimeField.setEnabled(enabled);
        localTimeField.setEnabled(enabled);
    }

    private class DateValueChangeListener implements Property.ValueChangeListener {


        public DateValueChangeListener() {
            super();
        }


        @Override
        public void valueChange(com.vaadin.data.Property.ValueChangeEvent event) {
            if (localTimeField.getValue() == null || localTimeField.getValue().equals("")){
                final DateTime dateTime = dateTimeField.getConvertedValue();

                final LocalTime localTime = new LocalTime(dateTime.getMillis(), getDateTimeZone());
                localTimeField.setConvertedValue(localTime);
            }

        }

    }

    public static DateTimeZone getInnerDateTimeZone() {
        return innerDateTimeZone;
    }

    public DateTimeFormatter getTimeFormatter() {
        return timeFormatter;
    }

    public DateTimeZone getDateTimeZone() {
        return dateTimeZone;
    }

    public LocalTimeField getLocalTimeField() {
        return localTimeField;
    }

    public DateTimeField getDateTimeField() {
        return dateTimeField;
    }

    public String getDefaultTime() {
        return defaultTime;
    }

    public Locale getDefaultLocale() {
        return defaultLocale;
    }

    public DateTimeFormatter getDateFormatter() {
        return dateFormatter;
    }

    @Override
    public void populateDateTimeShortCutListener(Locale locale) {
        final Collection<DateTimeShortCutListener> existingShortCutListeners = (Collection<DateTimeShortCutListener>) getListeners(DateTimeShortCutListener.class);
        if (CollectionUtils.isNotEmpty(existingShortCutListeners)) {
            for (final DateTimeShortCutListener shortCutListener : existingShortCutListeners) {
                removeListener(DateTimeShortCutListener.class, shortCutListener);
            }
        }
        final List<DateTimeShortCutListener> shortCutListeners = DateTimeShortCutListenerUtil.generateShortCutListener(locale, getDateTimeZone());
        if (CollectionUtils.isNotEmpty(shortCutListeners)) {
            this.addDateTimeShortCutListener(shortCutListeners);
        }
    }
}
