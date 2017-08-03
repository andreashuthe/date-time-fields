package org.vaadin.demo.ui.vaadin.login.presenter;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public VaadinLoginPresenter(VaadinLoginView view) {
        super(view);
    }

    @Override
    protected void doLogin(String userName, String password, boolean remember) {
        Authentication auth = new UsernamePasswordAuthenticationToken(userName, password);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        init();
    }
}
