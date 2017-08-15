package org.vaadin.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

/**
 * Created by huth on 24.07.2017.
 *//**
 * Configure Spring Security.
 */

public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private SpringCustomAuthProvider customAuthenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
/*
        http.authorizeRequests().antMatchers("/VAADIN/**", "/resources/**").permitAll();
        http.authorizeRequests().anyRequest().authenticated().and().formLogin().loginPage("/login")
                .successForwardUrl("/main").permitAll();

        http.headers().frameOptions().sameOrigin();*/
        http.authorizeRequests().antMatchers("/WEB-INF/**", "/VAADIN/**", "/PUSH/**", "/UIDL/**", "/HEARBEAT/**").permitAll().antMatchers("/").hasRole("USER");
        http.csrf().disable();
        http.formLogin().loginPage("/");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenticationProvider);
    }

}