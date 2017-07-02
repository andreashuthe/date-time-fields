package org.vaadin.mvp.event;

import org.vaadin.mvp.event.base.ObjectHandleEvent;

/**
 * Created by huth on 16.06.2017.
 */
public class NavigationEvent<T> extends ObjectHandleEvent<T> {

    /**
     *
     * @param value
     * @param objectClass
     */
    public NavigationEvent(T value, Class<T> objectClass) {
        super(value, objectClass);
    }
}
