package org.vaadin.mvp.handler;

/**
 * Created by huth on 09.06.2017.
 */
public interface LoginHandler {
    void login(String userName, String password, boolean remember);
}
