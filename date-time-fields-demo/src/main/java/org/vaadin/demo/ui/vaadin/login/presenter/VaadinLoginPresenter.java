package org.vaadin.demo.ui.vaadin.login.presenter;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.vaadin.demo.ui.presenter.login.LoginPresenter;
import org.vaadin.demo.ui.vaadin.login.view.VaadinLoginView;
import org.vaadin.mvp.base.presenter.VaadinNavigatablePresenter;
import org.vaadin.spring.security.shared.VaadinSharedSecurity;

/**
 * Created by huth on 13.07.2017.
 */
@UIScope
@SpringView(name = LoginPresenter.PRESENTER_NAME)
@CommonsLog
public class VaadinLoginPresenter extends LoginPresenter<VaadinLoginView> implements VaadinNavigatablePresenter<VaadinLoginView> {

    @Autowired
    private VaadinSharedSecurity vaadinSecurity;

    @Autowired
    public VaadinLoginPresenter(VaadinLoginView view) {
        super(view);
    }

    @Override
    protected void doLogin(String userName, String password, boolean remember) {

        try {

            final Authentication authentication = vaadinSecurity.login(userName, password, remember);
            int i = 0;

        } catch (AuthenticationException ex) {
            log.error("AuthenticationFailed", ex);
        } catch (Exception ex) {
            log.error("", ex);

        } finally {

        }


    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        init();
    }
}
