package org.vaadin.listener.util;

import org.joda.time.DateTimeZone;
import org.vaadin.listener.DateTimeShortCutListener;

import java.util.*;

/**
 * Created by andreas_h on 04.06.17.
 */
public class DateTimeShortCutListenerUtil {
    public final static int NOW = 0;
    public final static int YESTERDAY = -1;
    public final static int TOMORROW = 1;
    public final static int PLUS = 2;
    public final static int MINUS = -2;


    /**
     *
     * @param locale
     * @return
     */
    public static List<DateTimeShortCutListener> generateShortCutListener(Locale locale){
        return generateShortCutListener(locale, DateTimeZone.UTC);
    }

    /**
     *
     * @param locale
     * @param dateTimeZone
     * @return
     */
    public static List<DateTimeShortCutListener> generateShortCutListener(final Locale locale, final DateTimeZone dateTimeZone){

        final List<DateTimeShortCutListener> shortcutListenerList = new ArrayList<DateTimeShortCutListener>();

        final List<ShortCutDateEnum> shortCutDateEnums =  ShortCutDateEnum.getShortCutDateEnums(locale);

        final Map<Integer, Boolean> listenerAdd = new HashMap<Integer, Boolean>();

        if (shortCutDateEnums != null && shortCutDateEnums.size() > 0) {
            for (final ShortCutDateEnum shortCutDateEnum : shortCutDateEnums) {
                final DateTimeShortCutListener dateTimeShortCutListener = new DateTimeShortCutListener(locale, shortCutDateEnum, dateTimeZone);
                shortcutListenerList.add(dateTimeShortCutListener);
                listenerAdd.put(Integer.valueOf(shortCutDateEnum.getType()), Boolean.TRUE);
            }
        }

        final List<ShortCutDateEnum> shortCutDateEnumsNoLocale = ShortCutDateEnum.getShortCutDateEnums();
        for (final ShortCutDateEnum shortCutDateEnum : shortCutDateEnumsNoLocale) {
            if (!listenerAdd.containsKey(Integer.valueOf(shortCutDateEnum.getType()))) {
                final DateTimeShortCutListener dateTimeShortCutListener = new DateTimeShortCutListener(locale, shortCutDateEnum, dateTimeZone);
                shortcutListenerList.add(dateTimeShortCutListener);
                listenerAdd.put(Integer.valueOf(shortCutDateEnum.getType()), Boolean.TRUE);
            }
        }

        return shortcutListenerList;


    }

}
