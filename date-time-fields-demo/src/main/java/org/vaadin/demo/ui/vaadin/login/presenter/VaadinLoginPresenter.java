package org.vaadin.demo.ui.vaadin.login.presenter;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.vaadin.demo.event.LoginSuccessEvent;
import org.vaadin.demo.mapper.UserMapper;
import org.vaadin.demo.model.security.User;
import org.vaadin.demo.transfer.security.UserDto;
import org.vaadin.demo.ui.presenter.login.LoginPresenter;
import org.vaadin.demo.ui.vaadin.login.view.VaadinLoginView;
import org.vaadin.mvp.base.presenter.VaadinNavigatablePresenter;

/**
 * Created by huth on 13.07.2017.
 */
@UIScope
@SpringView(name = LoginPresenter.PRESENTER_NAME)
@CommonsLog
public class VaadinLoginPresenter extends LoginPresenter<VaadinLoginView> implements VaadinNavigatablePresenter<VaadinLoginView> {

    @Autowired
    protected AuthenticationManager authenticationManager;

    @Autowired
    public VaadinLoginPresenter(VaadinLoginView view) {
        super(view);
    }

    @Override
    protected void doLogin(String userName, String password, boolean remember) {

        try {
            final Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
            if (auth.isAuthenticated()) {
                final VaadinSession vaadinSession = VaadinSession.getCurrent();
                if (auth.getPrincipal() instanceof User) {
                    final UserDto userDto = UserMapper.INSTANCE.userToUserDto((User) auth.getPrincipal());
                    getEventBus().publish(this, new LoginSuccessEvent(userDto));
                }
                //    vaadinSession.setAttribute("user", auth.);
            }
        } catch (AuthenticationException ae) {
            log.error("User: " + userName + " login failed", ae );
        }
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        init();
    }
}
