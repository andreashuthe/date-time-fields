package org.vaadin.demo.ui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;
import org.vaadin.demo.ui.presenter.login.LoginPresenter;
import org.vaadin.mvp.base.presenter.VaadinNavigatablePresenter;

@SpringUI(path = "/login")
@SpringView
public class LoginUI extends UI implements ViewDisplay {

    @Override
    protected void init(VaadinRequest request) {

        getNavigator().navigateTo(LoginPresenter.PRESENTER_NAME);
    }

    @Override
    public void showView(View view) {
        if (view instanceof VaadinNavigatablePresenter) {
            setContent((Component) ((VaadinNavigatablePresenter) view).getView());
            setSizeFull();
        }
    }
}
