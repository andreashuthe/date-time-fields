package org.vaadin.demo;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import org.joda.time.DateTimeZone;
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
import org.vaadin.listener.DateTimeShortCutListener;
import org.vaadin.listener.util.DateTimeShortCutListenerUtil;

import java.util.List;
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

        layout.addComponent(new Button("Show values", new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                try {
                    fieldGroup.commit();
                    demoBean.setDateTimeAndLocalTime(dateTimeAndLocalTimeField.getValue());
                } catch (CommitException e) {
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

        setContent(layout);

    }
}
