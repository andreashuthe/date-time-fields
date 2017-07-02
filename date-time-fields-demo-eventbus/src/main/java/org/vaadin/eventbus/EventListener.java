package org.vaadin.eventbus;

import com.google.common.eventbus.Subscribe;

/**
 *
 * @param <T>
 */
public interface EventListener<T> {

    void onEvent(T event);
}
