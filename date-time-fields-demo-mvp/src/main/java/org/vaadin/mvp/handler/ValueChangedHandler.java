package org.vaadin.mvp.handler;

/**
 * Created by huth on 09.06.2017.
 */
public interface ValueChangedHandler {
    void handleChanged(String propertyId, Object value);
}
