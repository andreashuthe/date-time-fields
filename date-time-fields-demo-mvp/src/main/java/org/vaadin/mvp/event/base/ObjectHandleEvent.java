package org.vaadin.mvp.event.base;

import lombok.Getter;

/**
 * Created by huth on 16.06.2017.
 */
public abstract class ObjectHandleEvent<T> implements Event<T>{

    @Getter private final T value;
    @Getter private final Class<T> objectClass;

    public ObjectHandleEvent(final T value, final Class<T> objectClass) {
        this.value = value;
        this.objectClass = objectClass;
    }
}
