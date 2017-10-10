package org.vaadin.demo.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.demo.context.ISpringSecurityCallContext;
import org.vaadin.demo.ui.presenter.login.LoginPresenter;
import org.vaadin.demo.ui.vaadin.navigation.presenter.VaadinNavigationPresenter;
import org.vaadin.mvp.base.presenter.VaadinNavigatablePresenter;
import org.vaadin.viritin.layouts.MCssLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;


@SpringUI
@Title("date-time-fields Add-on Demo")
@SpringViewDisplay
@Theme("demotheme")
@SuppressWarnings("serial")
public class DemoUI extends UI implements ViewDisplay {
    @Autowired private VaadinNavigationPresenter navigationPresenter;
    @Autowired @Getter SpringViewProvider viewProvider;

    @Autowired private ISpringSecurityCallContext callContext;

    private MCssLayout header;
    private MCssLayout content;
    private MCssLayout footer;

    @Override
    protected void init(VaadinRequest request) {
        initBaseContent();
        initNavigationPresenter();

        navigate(LoginPresenter.PRESENTER_NAME);
    }

    private void initBaseContent() {
        header = new MCssLayout();
        content = new MCssLayout();
        footer = new MCssLayout();

        final MVerticalLayout root = new MVerticalLayout().withSpacing(false).withMargin(false).with(header, content, footer);
        setContent(root);
    }


    private void initNavigationPresenter() {
        navigationPresenter.init();
        header.addComponent(navigationPresenter.getView());
    }


    @Override
    public void showView(View view) {
        if (view instanceof VaadinNavigatablePresenter) {
            content.removeAllComponents();
            content.add((Component) ((VaadinNavigatablePresenter) view).getView());
        }
    }

    private void navigate (String presenterName) {
        getNavigator().navigateTo(presenterName);
    }

    /*
    @Subscribe
    public void onEvent(NavigationEvent<String> navigationEvent) {
        navigate(navigationEvent.getValue());
    }

    @Subscribe
    public void onEvent(LoginSuccessEventI<UserDto> loginSuccessEvent) {
        final VaadinSession vaadinSession = VaadinSession.getCurrent();
        final UserDto userDto = loginSuccessEvent.getValue();
        if (userDto != null) {
            vaadinSession.setAttribute("user", userDto);
        } else {
            vaadinSession.setAttribute("user", null);
        }

    }

    @Subscribe
    public void onEvent(LoginFailedEvent<String> loginFailedEvent) {

    }
    */

}
