package org.vaadin.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionFixationProtectionStrategy;
import org.vaadin.spring.annotation.EnableVaadinExtensions;
import org.vaadin.spring.http.HttpService;
import org.vaadin.spring.security.annotation.EnableVaadinSharedSecurity;
import org.vaadin.spring.security.config.VaadinSharedSecurityConfiguration;
import org.vaadin.spring.security.shared.VaadinAuthenticationSuccessHandler;
import org.vaadin.spring.security.shared.VaadinSessionClosingLogoutHandler;
import org.vaadin.spring.security.shared.VaadinUrlAuthenticationSuccessHandler;
import org.vaadin.spring.security.web.VaadinRedirectStrategy;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by huth on 24.07.2017.
 *//**
 * Configure Spring Security.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, proxyTargetClass = true)
@EnableVaadinExtensions
@EnableVaadinSharedSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("user").roles("USER").and().withUser("admin")
                .password("admin").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable(); // Use Vaadin's built-in CSRF protection instead
        http.authorizeRequests().antMatchers("/login/**").anonymous().antMatchers("/vaadinServlet/UIDL/**")
                .permitAll().antMatchers("/vaadinServlet/HEARTBEAT/**").permitAll().anyRequest().authenticated();
        http.httpBasic().disable();
        http.formLogin().disable();
        // Remember to add the VaadinSessionClosingLogoutHandler
        http.logout()
                .addLogoutHandler(new VaadinSessionClosingLogoutHandler())
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout").permitAll();
        http.exceptionHandling()
                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
                .accessDeniedHandler(new AccessDeniedHandlerImpl() {
                    @Override
                    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
                        if (!response.isCommitted()) {

                            RequestDispatcher dispatcher = request.getRequestDispatcher("/error");
                            dispatcher.forward(request, response);
                        }

                    }
                });
        // Instruct Spring Security to use the same RememberMeServices as Vaadin4Spring. Also remember the key.
        http.rememberMe().rememberMeServices(rememberMeServices()).key("myAppKey");
        // Instruct Spring Security to use the same authentication strategy as Vaadin4Spring
        http.sessionManagement().sessionAuthenticationStrategy(sessionAuthenticationStrategy());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/*");
    }

    /**
     * The {@link AuthenticationManager} must be available as a Spring bean for Vaadin4Spring.
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * The {@link RememberMeServices} must be available as a Spring bean for Vaadin4Spring.
     */
    @Bean
    public RememberMeServices rememberMeServices() {
        return new TokenBasedRememberMeServices("myAppKey", userDetailsService());
    }

    /**
     * The {@link SessionAuthenticationStrategy} must be available as a Spring bean for Vaadin4Spring.
     */
    @Bean
    public SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new SessionFixationProtectionStrategy();
    }

    @Bean(name = VaadinSharedSecurityConfiguration.VAADIN_AUTHENTICATION_SUCCESS_HANDLER_BEAN)
    public VaadinAuthenticationSuccessHandler vaadinAuthenticationSuccessHandler(HttpService httpService,
                                                                                 VaadinRedirectStrategy vaadinRedirectStrategy) {
        return new VaadinUrlAuthenticationSuccessHandler(httpService, vaadinRedirectStrategy, "/");
    }

}