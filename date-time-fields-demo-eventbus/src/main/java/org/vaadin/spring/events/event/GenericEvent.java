package org.vaadin.spring.events.event;

public abstract class GenericEvent<T> implements IGenericEvent<T> {
    private final T value;

    protected GenericEvent(T value) {
        this.value = value;
    }

    @Override
    public T getValue() {
        return value;
    }
}
