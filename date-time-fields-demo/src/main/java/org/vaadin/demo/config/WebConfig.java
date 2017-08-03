package org.vaadin.demo.config;

import com.vaadin.spring.annotation.EnableVaadin;
import com.vaadin.spring.annotation.EnableVaadinNavigation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.vaadin.eventbus.EnableEventBus;
import org.vaadin.eventbus.SpringEventBus;

/**
 * Created by huth on 16.06.2017.
 */
@Configuration
@EnableAsync
@EnableVaadin
@EnableVaadinNavigation
@EnableEventBus
@ComponentScan(basePackages = {
        "org.vaadin.mvp",
        "org.vaadin.demo.context",
        "org.vaadin.demo.service",
        "org.vaadin.demo.ui",
        "org.vaadin.demo.ui.presenter",
        "org.vaadin.demo.ui.view"
})
public class WebConfig {

    @Bean protected SpringEventBus eventBus() {
        return new SpringEventBus();
    }

}
