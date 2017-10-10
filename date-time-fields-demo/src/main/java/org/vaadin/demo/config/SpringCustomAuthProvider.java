package org.vaadin.demo.config;

import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.vaadin.demo.model.security.User;
import org.vaadin.demo.repositories.UserRepository;

import java.util.List;

@Component
public class SpringCustomAuthProvider implements AuthenticationProvider {

    @Autowired UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final String name = authentication.getName();
        final String password = authentication.getCredentials().toString();

        final User user;
        if (StringUtils.isBlank(name) || StringUtils.isBlank(password)) {
            user = null;
            throw new BadCredentialsException("No user or password entered");
        } else {
            user = userRepository.findFirstByUsername(name);
        }
        if (user == null) {
            throw new BadCredentialsException("Username not found.");
        } else {
            if (name.equals(user.getUsername()) && password.equals(user.getPassword())) {
                final List<GrantedAuthority> grantedAuthorities = Lists.newArrayList();
                grantedAuthorities.add(new SimpleGrantedAuthority("USER"));
                final Authentication result = new UsernamePasswordAuthenticationToken(user, password, grantedAuthorities);
                return result;
            }

        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }

}