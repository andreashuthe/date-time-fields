package org.vaadin.mvp.event.base;

/**
 * Created by huth on 16.06.2017.
 */
public interface Event<O> {
    Class<O> getObjectClass();

}
