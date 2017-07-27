package org.vaadin.demo.ui.view.login;

import org.vaadin.mvp.base.view.View;
import org.vaadin.mvp.handler.LoginHandler;

/**
 * Created by huth on 13.07.2017.
 */
public interface LoginView extends View {
    String VIEW_NAME = "loginView";

    void setLoginHandler(LoginHandler loginHandler);
}
