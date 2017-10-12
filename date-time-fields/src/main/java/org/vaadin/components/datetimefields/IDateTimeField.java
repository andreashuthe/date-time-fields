package org.vaadin.components.datetimefields;

import org.vaadin.listener.DateTimeShortCutListener;

import java.util.List;
import java.util.Locale;

/**
 * Created by andreas_h on 05.06.17.
 */
public interface IDateTimeField {
    void addDateTimeShortCutListener(List<DateTimeShortCutListener> shortCutListeners);

    void populateDateTimeShortCutListener(Locale locale);
}
