package org.vaadin.demo.model.context;

import org.vaadin.demo.model.security.User;

import java.util.Locale;

/**
 * Created by huth on 19.07.2017.
 */
public interface ICallContext {
    User getUser();
    Locale getLocale();
}
