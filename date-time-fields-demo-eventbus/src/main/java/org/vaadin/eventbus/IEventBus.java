package org.vaadin.eventbus;

/**
 *
 */
public interface IEventBus {

    /**
     *
     * @param event
     * @param <T>
     */
    <T> void publish(T event);

    /**
     *
     * @param listener
     */
    void register(EventListener listener);
}
