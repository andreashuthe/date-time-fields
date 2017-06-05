package org.vaadin.demo;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import org.vaadin.components.datetimefields.*;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.listener.util.DateTimeShortCutListenerUtil;

import java.util.Locale;

@Title("date-time-fields Add-on Demo")
@Theme("valo")
@SuppressWarnings("serial")
public class DemoUI extends UI {

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(
            productionMode = false,
            ui = DemoUI.class,
            widgetset = "org.vaadin.demo.DemoWidgetSet")
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {

        final DateTimeDemoBean demoBean = new DateTimeDemoBean();
        BeanItem<DateTimeDemoBean> demoBeanItem = new BeanItem<DateTimeDemoBean>(demoBean);

        final FieldGroup fieldGroup = new FieldGroup(demoBeanItem);

        // We need to set the FieldFactory so that Joda-Time classes are recognized
        fieldGroup.setFieldFactory(new DateTimeFieldGroupFieldFactory());

        // Initialize our new UI component
        final DateTimeField dateTimeField = (DateTimeField) fieldGroup.buildAndBind("dateTime");
        final IntervalField intervalField = (IntervalField) fieldGroup.buildAndBind("interval");
        final LocalTimeField localTimeField = (LocalTimeField) fieldGroup.buildAndBind("localTime");
        final DateTimeAndLocalTimeField dateTimeAndLocalTimeField = (DateTimeAndLocalTimeField) fieldGroup.buildAndBind("dateTimeAndlocalTime");


        dateTimeField.addDateTimeShortCutListener(DateTimeShortCutListenerUtil.generateShortCutListener(Locale.getDefault()));
        intervalField.addDateTimeShortCutListener(DateTimeShortCutListenerUtil.generateShortCutListener(Locale.getDefault()));
        dateTimeAndLocalTimeField.addDateTimeShortCutListener(DateTimeShortCutListenerUtil.generateShortCutListener(Locale.getDefault()));

        // Show it in the middle of the screen
        final VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.addComponent(dateTimeField);
        layout.addComponent(intervalField);
        layout.addComponent(localTimeField);
        layout.addComponent(dateTimeAndLocalTimeField);

        layout.addComponent(new Button("Show values", new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                try {
                    fieldGroup.commit();
                } catch (CommitException e) {
                    e.printStackTrace();
                }
                final StringBuilder valueString = new StringBuilder();

                valueString.append("dateTime: ").append(demoBean.getDateTime().toString()).append("\n");
                valueString.append("interval: ").append(demoBean.getInterval().toString()).append("\n");
                valueString.append("localTime: ").append(demoBean.getLocalTime().toString());
                valueString.append("dateTimeAndlocalTime: ").append(demoBean.getDateTimeAndLocalTime().toString());

                Notification.show(valueString.toString());
            }
        }));

        setContent(layout);

    }
}
