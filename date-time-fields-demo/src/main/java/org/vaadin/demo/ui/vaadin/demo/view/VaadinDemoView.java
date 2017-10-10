package org.vaadin.demo.ui.vaadin.demo.view;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.event.ShortcutAction;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.components.datetimefields.DateTimeAndLocalTimeField;
import org.vaadin.components.datetimefields.DateTimeField;
import org.vaadin.components.datetimefields.DateTimeFieldGroupFieldFactory;
import org.vaadin.components.datetimefields.IntervalField;
import org.vaadin.components.datetimefields.LocalTimeField;
import org.vaadin.demo.DateTimeDemoBean;
import org.vaadin.demo.ui.theme.DemoTheme;
import org.vaadin.demo.ui.view.demo.DemoView;
import org.vaadin.demo.ui.view.login.LoginView;
import org.vaadin.listener.DateTimeShortCutListener;
import org.vaadin.listener.util.DateTimeShortCutListenerUtil;
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

import java.util.List;
import java.util.Locale;

/**
 * Created by huth on 13.07.2017.
 */
@UIScope
@SpringComponent
public class VaadinDemoView extends MCssLayout implements DemoView {

    @Autowired @Getter IVaadinComponentFactory vaadinComponentFactory;

    @Autowired
    public VaadinDemoView() {


        this.setSizeFull();
    }

    @Override
    public void init() {
        final DateTimeDemoBean demoBean = new DateTimeDemoBean();
        final BeanItem<DateTimeDemoBean> demoBeanItem = new BeanItem<DateTimeDemoBean>(demoBean);

        final FieldGroup fieldGroup = new FieldGroup(demoBeanItem);

        // We need to set the FieldFactory so that Joda-Time classes are recognized
        fieldGroup.setFieldFactory(new DateTimeFieldGroupFieldFactory());

        // Initialize our new UI component
        final DateTimeField dateTimeField = (DateTimeField) fieldGroup.buildAndBind("dateTime");
        final IntervalField intervalField = (IntervalField) fieldGroup.buildAndBind("interval");
        final LocalTimeField localTimeField = (LocalTimeField) fieldGroup.buildAndBind("localTime");
        final DateTimeAndLocalTimeField dateTimeAndLocalTimeField = new DateTimeAndLocalTimeField(DateTimeZone.UTC, Locale.getDefault(), null);


        final List<DateTimeShortCutListener> shortCutListeners = DateTimeShortCutListenerUtil.generateShortCutListener(Locale.getDefault());
        if (shortCutListeners != null && shortCutListeners.size() > 1) {
            for (DateTimeShortCutListener shortCutListener : shortCutListeners) {
                shortCutListener.enableDevMode();
            }
        }
        dateTimeField.addDateTimeShortCutListener(shortCutListeners);

        intervalField.addDateTimeShortCutListener(shortCutListeners);

        dateTimeAndLocalTimeField.addDateTimeShortCutListener(shortCutListeners);


        // Show it in the middle of the screen
        final VerticalLayout layout = new VerticalLayout();


        layout.setSizeFull();
        layout.addComponent(dateTimeField);
        layout.addComponent(intervalField);
        layout.addComponent(localTimeField);
        layout.addComponent(dateTimeAndLocalTimeField);

        layout.addComponent(new Button("Show values", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    fieldGroup.commit();
                    demoBean.setDateTimeAndLocalTime(dateTimeAndLocalTimeField.getValue());
                } catch (FieldGroup.CommitException e) {
                    e.printStackTrace();
                }
                final StringBuilder valueString = new StringBuilder();

                valueString.append("dateTime: ").append(demoBean.getDateTime() == null ? "no Value" : demoBean.getDateTime().toString()).append("\n");
                valueString.append("interval: ").append(demoBean.getInterval() == null ? "no Value" : demoBean.getInterval().toString()).append("\n");
                valueString.append("localTime: ").append(demoBean.getLocalTime() == null ? "no Value" : demoBean.getLocalTime().toString().toString()).append("\n");
                valueString.append("dateTimeAndlocalTime: ").append(demoBean.getDateTimeAndLocalTime() == null ? "no Value" : demoBean.getDateTimeAndLocalTime().toString());

                Notification.show(valueString.toString());
            }
        }));

        add(layout);
    }


    @Override
    public String getName() {
        return LoginView.VIEW_NAME;
    }

}
