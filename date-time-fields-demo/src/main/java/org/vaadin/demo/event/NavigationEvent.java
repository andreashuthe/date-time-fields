package org.vaadin.demo.event;

import org.vaadin.spring.events.event.GenericEvent;

public class NavigationEvent extends GenericEvent<String> {

    public NavigationEvent(String value) {
        super(value);
    }
}
