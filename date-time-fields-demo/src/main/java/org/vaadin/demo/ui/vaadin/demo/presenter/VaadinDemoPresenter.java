package org.vaadin.demo.ui.vaadin.demo.presenter;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.vaadin.demo.ui.presenter.demo.DemoPresenter;
import org.vaadin.demo.ui.presenter.login.LoginPresenter;
import org.vaadin.demo.ui.vaadin.demo.view.VaadinDemoView;
import org.vaadin.demo.ui.vaadin.login.view.VaadinLoginView;
import org.vaadin.mvp.base.presenter.VaadinNavigatablePresenter;

/**
 * Created by huth on 13.07.2017.
 */
@UIScope
@SpringView(name = DemoPresenter.PRESENTER_NAME)
@CommonsLog
public class VaadinDemoPresenter extends DemoPresenter<VaadinDemoView> implements VaadinNavigatablePresenter<VaadinDemoView> {

    @Autowired
    public VaadinDemoPresenter(VaadinDemoView view) {
        super(view);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        init();
    }
}
