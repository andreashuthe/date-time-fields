package org.vaadin.eventbus;

import com.google.common.eventbus.EventBus;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * Project: SpringEventBus
 * Module Desc:org.vaadin.eventbus
 * User: zjprevenge
 * Date: 2017/1/11
 * Time: 9:45
 */
public class SpringEventBus extends EventBus implements IEventBus {

    /**
     *
     * @param event
     * @param <T>
     */
    public <T> void publish(T event) {

        if (TransactionSynchronizationManager.isActualTransactionActive()) {
            TransactionSynchronizationManager.registerSynchronization(
                    new EventBusTransactionSynchronization<T>(this, event));
        } else {
            super.post(event);
        }
    }

    /**
     *
     * @param listener
     */
    public void register(EventListener listener) {
        super.register(listener);
    }
}
