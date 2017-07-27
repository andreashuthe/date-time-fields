package org.vaadin.demo.context.impl;

import com.google.common.collect.Lists;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.vaadin.demo.context.ISpringSecurityCallContext;
import org.vaadin.demo.model.security.User;

import java.util.Locale;

/**
 * Created by huth on 19.07.2017.
 */
@Component(value = "callContext")
public class SpringSecurityCallContext implements ISpringSecurityCallContext {
    private Locale locale;

    @Override
    public User getUser() {
        final SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext != null) {
            Authentication authentication = securityContext.getAuthentication();
            if (authentication != null) {
                return (User) authentication.getPrincipal();
            }
        }
        return null;
    }

    @Override
    public void setUser(User user) {
        final Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getUsername(), Lists.newArrayList(new SimpleGrantedAuthority("USER")));
        final SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
    }

    @Override
    public Locale getLocale() {
        return locale;
    }

    @Override
    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
