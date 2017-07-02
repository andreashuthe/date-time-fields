package org.vaadin.eventbus;

import com.google.common.eventbus.EventBus;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;

/**
 *
 * @param <T>
 */
public class EventBusTransactionSynchronization<T> extends TransactionSynchronizationAdapter {

    private EventBus eventBus;

    private T event;

    public EventBusTransactionSynchronization(EventBus eventBus, T event) {
        this.eventBus = eventBus;
        this.event = event;
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public T getEvent() {
        return event;
    }

    public void setEvent(T event) {
        this.event = event;
    }

    /**
     *
     */
    @Override
    public void afterCommit() {
        super.afterCommit();
        eventBus.post(event);
    }
}
