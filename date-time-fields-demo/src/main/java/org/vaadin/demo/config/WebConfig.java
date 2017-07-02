package org.vaadin.demo.config;

import com.vaadin.spring.annotation.EnableVaadin;
import com.vaadin.spring.annotation.EnableVaadinNavigation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.vaadin.eventbus.EnableEventBus;
import org.vaadin.eventbus.SpringEventBus;

/**
 * Created by huth on 16.06.2017.
 */
@Configuration
@EnableVaadin
@EnableVaadinNavigation
@EnableEventBus
@ComponentScan(basePackages = {
        "org.vaadin.demo.ui",
        "org.vaadin.demo.ui.presenter",
        "org.vaadin.demo.ui.view"
})

public class WebConfig {

    @Bean protected SpringEventBus eventBus() {
        return new SpringEventBus();
    }

}
