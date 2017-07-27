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
import org.vaadin.spring.http.HttpService;
import org.vaadin.spring.security.annotation.EnableVaadinSharedSecurity;
import org.vaadin.spring.security.config.VaadinSharedSecurityConfiguration;
import org.vaadin.spring.security.shared.VaadinAuthenticationSuccessHandler;
import org.vaadin.spring.security.shared.VaadinUrlAuthenticationSuccessHandler;
import org.vaadin.spring.security.web.VaadinRedirectStrategy;

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
