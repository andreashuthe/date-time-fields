package org.vaadin.demo.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.demo.context.ISpringSecurityCallContext;
import org.vaadin.demo.ui.vaadin.login.presenter.VaadinLoginPresenter;

@SpringUI(path = "/login")
@Title("date-time-fields Add-on Demo")
@Theme("demotheme")
@SuppressWarnings("serial")
public class LoginUI extends UI {
    @Autowired private VaadinLoginPresenter vaadinLoginPresenter;


    @Autowired private ISpringSecurityCallContext callContext;


    @Override
    protected void init(VaadinRequest request) {
        initContent();
    }




    private void initContent() {
        setContent(vaadinLoginPresenter.getView());
        setSizeFull();

    }

}
