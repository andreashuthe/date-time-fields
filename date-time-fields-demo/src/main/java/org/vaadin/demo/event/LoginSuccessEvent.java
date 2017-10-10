package org.vaadin.demo.event;

import org.vaadin.demo.transfer.security.UserDto;
import org.vaadin.spring.events.event.GenericEvent;

public class LoginSuccessEvent extends GenericEvent<UserDto> {

    public LoginSuccessEvent(UserDto value) {
        super(value);
    }
}
