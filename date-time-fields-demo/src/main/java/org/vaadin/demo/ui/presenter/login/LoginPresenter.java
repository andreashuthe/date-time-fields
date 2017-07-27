package org.vaadin.demo.ui.presenter.login;

import org.vaadin.demo.ui.view.login.LoginView;
import org.vaadin.mvp.base.presenter.BasePresenter;
import org.vaadin.mvp.handler.LoginHandler;

/**
 * Created by huth on 13.07.2017.
 */
public abstract class LoginPresenter<V extends LoginView> extends BasePresenter<V> {

    public static final String PRESENTER_NAME = "loginPresenter";

    public LoginPresenter(V view) {
        super(view);

    }

    @Override
    protected void init() {
        super.init();
        getView().setLoginHandler(new LoginHandler() {
            @Override
            public void login(String userName, String password, boolean remember) {
                doLogin(userName, password, remember);
            }
        });
    }

    protected abstract void doLogin(String userName, String password, boolean remember);
}
