package org.vaadin.demo.ui.vaadin.login.view;

import com.vaadin.event.ShortcutAction;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Component;
import com.vaadin.ui.themes.ValoTheme;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.demo.ui.theme.DemoTheme;
import org.vaadin.demo.ui.view.login.LoginView;
import org.vaadin.mvp.handler.LoginHandler;
import org.vaadin.mvp.util.IVaadinComponentFactory;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.fields.MCheckBox;
import org.vaadin.viritin.fields.MPasswordField;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.label.MLabel;
import org.vaadin.viritin.layouts.MCssLayout;
import org.vaadin.viritin.layouts.MFormLayout;
import org.vaadin.viritin.layouts.MWindow;

/**
 * Created by huth on 13.07.2017.
 */
@UIScope
@SpringComponent
public class VaadinLoginView extends MCssLayout implements LoginView {

    @Autowired @Getter IVaadinComponentFactory vaadinComponentFactory;

    @Getter @Setter private LoginHandler loginHandler;

    private MButton loginButton;
    private MCheckBox rememberMe;
    private MTextField usernameField;
    private MPasswordField passwordField;
    private MLabel loginFailedLabel;
    private MButton clearButton;
    private final MWindow helpWindow;


    @Autowired
    public VaadinLoginView() {


        helpWindow = new MWindow("Test");
        helpWindow.setContent(new MLabel().withContent("TestLabel"));
        helpWindow.center();
        helpWindow.setModal(true);
        helpWindow.setSizeUndefined();

        this.setSizeFull();
    }

    @Override
    public void init() {
        add(buildLoginForm());
    }

    private Component buildLoginForm() {
        final MFormLayout loginForm = new MFormLayout();

        loginForm.addStyleName(DemoTheme.LOGIN_FORM);
        loginForm.setSizeUndefined();
        loginForm.setMargin(false);



        loginForm.with(usernameField = getVaadinComponentFactory().createTextFieldWithLabel("username", true));
        usernameField.setWidth(15, Unit.EM);
        loginForm.addComponent(passwordField = getVaadinComponentFactory().createPasswordField("password", true));
        passwordField.withWidth(15, Unit.EM);
        passwordField.setDescription("Write anything");
        MCssLayout buttons = new MCssLayout();
        buttons.withStyleName("buttons");
        loginForm.with(buttons);

        buttons.addComponent(loginButton = getVaadinComponentFactory().createCommitButton());
        loginButton.setDisableOnClick(true);
        loginButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        loginButton.addStyleName(DemoTheme.BUTTON_FRIENDLY);
        loginButton.setDisableOnClick(true);
        loginButton.addClickListener(new MButton.MClickListener() {
            @Override
            public void onClick() {
                getLoginHandler().login(usernameField.getValue(), passwordField.getValue(), rememberMe.getValue());
            }
        });

        rememberMe = new MCheckBox();
        buttons.addComponent(rememberMe);

        loginForm.addComponent(loginFailedLabel = new MLabel());
        loginFailedLabel.setSizeUndefined();
        loginFailedLabel.addStyleName(ValoTheme.LABEL_FAILURE);
        loginFailedLabel.setVisible(false);

        return loginForm;
    }


    @Override
    public String getName() {
        return LoginView.VIEW_NAME;
    }

}
