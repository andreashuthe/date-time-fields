package org.vaadin.listener;

import com.vaadin.event.ShortcutListener;
import com.vaadin.ui.DateField;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.vaadin.components.datetimefields.DateTimeField;
import org.vaadin.listener.util.DateTimeShortCutListenerUtil;
import org.vaadin.listener.util.ShortCutDateEnum;

import java.util.Locale;

/**
 * Created by andreas_h on 04.06.17.
 */
public class DateTimeShortCutListener extends ShortcutListener {

    private final Locale locale;
    private final DateTimeZone dateTimeZone;
    private final ShortCutDateEnum shortCutDateEnum;

    /**
     *
     * @param locale
     * @param shortCutDateEnum
     * @param dateTimeZone
     */
    public DateTimeShortCutListener(Locale locale, ShortCutDateEnum shortCutDateEnum, DateTimeZone dateTimeZone) {
        this (locale, shortCutDateEnum, dateTimeZone, null);
    }

    /**
     *
     * @param locale
     * @param shortCutDateEnum
     * @param dateTimeZone
     * @param caption
     */
    public DateTimeShortCutListener(Locale locale, ShortCutDateEnum shortCutDateEnum, DateTimeZone dateTimeZone, String caption) {
        super(caption == null ? shortCutDateEnum.getDesc() : caption, shortCutDateEnum.getKeyCode(), null);
        this.locale = locale;
        this.dateTimeZone = dateTimeZone;
        this.shortCutDateEnum = shortCutDateEnum;
    }

    @Override
    public void handleAction(Object sender, Object target) {
        if (target instanceof DateTimeField) {

            final DateTimeField field = (DateTimeField) target;
            calcDateTimeFieldValue(field, getDateTimeZone());
        } else {
            throw new IllegalArgumentException("target is not a DateField");
        }
    }


    private void calcDateTimeFieldValue (final DateTimeField dateTimeField, final DateTimeZone dateTimeZone){

        final DateTime dateTime;

        switch (getShortCutDateEnum().getType()){
            case DateTimeShortCutListenerUtil.PLUS:
                final DateTime dateTimeFromFieldPlus = getDateTimeFromFieldOrNow(dateTimeField);
                dateTime = dateTimeFromFieldPlus.plusDays(1);
                break;
            case DateTimeShortCutListenerUtil.MINUS:
                final DateTime dateTimeFromFieldMinus = getDateTimeFromFieldOrNow(dateTimeField);
                dateTime = dateTimeFromFieldMinus.minusDays(1);
                break;
            case DateTimeShortCutListenerUtil.YESTERDAY:
                dateTime = DateTime.now(dateTimeZone).minusDays(1);
                break;
            case DateTimeShortCutListenerUtil.TOMORROW:
                dateTime = DateTime.now(dateTimeZone).plusDays(1);
                break;
            case DateTimeShortCutListenerUtil.NOW:
                dateTime = DateTime.now(dateTimeZone);
                break;
            default:
                dateTime = null;
                break;
        }

        if (dateTime != null) {
            dateTimeField.setValue(dateTime.toDate());
        }
    }

    private DateTime getDateTimeFromFieldOrNow(DateField dateTimeField) {
        DateTime dateTime = (DateTime) dateTimeField.getConvertedValue();
        if (dateTime == null) {
            dateTime = DateTime.now(dateTimeZone);
        }
        return dateTime;
    }

    public Locale getLocale() {
        return locale;
    }

    public DateTimeZone getDateTimeZone() {
        return dateTimeZone;
    }

    public ShortCutDateEnum getShortCutDateEnum() {
        return shortCutDateEnum;
    }
}
