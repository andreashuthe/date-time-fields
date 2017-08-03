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
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired private SpringCustomAuthProvider customAuthenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
/*
        http.authorizeRequests().antMatchers("/VAADIN/**", "/resources/**").permitAll();
        http.authorizeRequests().anyRequest().authenticated().and().formLogin().loginPage("/login")
                .successForwardUrl("/main").permitAll();

        http.headers().frameOptions().sameOrigin();*/

        http.csrf().disable();
        http.exceptionHandling()
                .authenticationEntryPoint(
                        new LoginUrlAuthenticationEntryPoint("/login/#!loginPresenter"))
                .accessDeniedPage("/accessDenied").and().authorizeRequests()
                .antMatchers("/VAADIN/**", "/PUSH/**", "/UIDL/**", "/login", "/login/**", "/error/**",
                        "/accessDenied/**", "/vaadinServlet/**")
                .permitAll().antMatchers("/main", "/**").fullyAuthenticated();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenticationProvider);
    }

}