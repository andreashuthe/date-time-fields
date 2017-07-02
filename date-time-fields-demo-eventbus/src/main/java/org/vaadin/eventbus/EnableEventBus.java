package org.vaadin.eventbus;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(EventBusConfiguration.class)
@Documented
public @interface EnableEventBus {
}
