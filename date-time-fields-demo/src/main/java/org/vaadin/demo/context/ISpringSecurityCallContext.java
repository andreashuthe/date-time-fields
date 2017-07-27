package org.vaadin.demo.context;

import org.vaadin.demo.model.context.ICallContext;
import org.vaadin.demo.model.security.User;

import java.util.Locale;

/**
 * Created by huth on 19.07.2017.
 */
public interface ISpringSecurityCallContext extends ICallContext {
    void setUser(User user);
    void setLocale(Locale locale);
}
