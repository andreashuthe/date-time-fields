package org.vaadin.eventbus;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 *
 */
@Configuration
public class EventBusConfiguration {

    @Resource
    private SpringEventBus springEventBus;

    @Bean
    public EventBusRegisterBeanPostProcessor eventBusRegisterBeanPostProcessor() {
        return new EventBusRegisterBeanPostProcessor(springEventBus);
    }
}
