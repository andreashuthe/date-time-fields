package org.vaadin.demo.ui.vaadin.navigation.presenter;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.demo.transfer.security.UserDto;
import org.vaadin.demo.ui.presenter.login.LoginPresenter;
import org.vaadin.demo.ui.presenter.navigation.NavigationPresenter;
import org.vaadin.demo.ui.vaadin.login.presenter.VaadinLoginPresenter;
import org.vaadin.demo.ui.vaadin.navigation.view.VaadinNavigationView;

/**
 * Created by huth on 16.06.2017.
 */
@UIScope
@SpringComponent
public class VaadinNavigationPresenter extends NavigationPresenter<VaadinNavigationView> {
    @Autowired
    public VaadinNavigationPresenter(VaadinNavigationView view) {
        super(view);
    }

    @Override
    protected void navigate(String name) {
        UI.getCurrent().getNavigator().navigateTo(name);
    }

    @Override
    protected void login(UserDto userDto) {
        final VaadinSession vaadinSession = VaadinSession.getCurrent();
        if (userDto != null) {
            vaadinSession.setAttribute("user", userDto);
        } else {
            vaadinSession.setAttribute("user", null);
        }
    }

    @Override
    public void init() {
        super.init();
        UI.getCurrent().getNavigator().addViewChangeListener(new ViewChangeListener() {
            @Override
            public boolean beforeViewChange(ViewChangeEvent event) {

                final VaadinSession vaadinSession = VaadinSession.getCurrent();
                final boolean isLoggedIn = vaadinSession.getAttribute("user") != null;
                final boolean isLoginView = event.getNewView() instanceof VaadinLoginPresenter;

                if(!isLoggedIn && !isLoginView) {
                    navigate(LoginPresenter.PRESENTER_NAME);
                    return false;
                } else if (isLoggedIn && isLoginView) {
                    return false;
                }
                return true;
            }

            @Override
            public void afterViewChange(ViewChangeEvent event) {

            }
        });
    }
}
