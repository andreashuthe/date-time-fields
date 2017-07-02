package org.vaadin.demo.ui.vaadin.appointment.view.edit;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.AbstractField;
import lombok.extern.apachecommons.CommonsLog;
import org.vaadin.components.datetimefields.DateTimeAndLocalTimeField;
import org.vaadin.demo.model.Appointment;
import org.vaadin.demo.ui.view.appointment.edit.AppointmentEditView;
import org.vaadin.mvp.base.view.edit.VaadinBaseEditView;
import org.vaadin.mvp.util.VaadinUiUtil;
import org.vaadin.viritin.fields.MTextField;

/**
 * Created by huth on 09.06.2017.
 */
@UIScope
@SpringComponent
public class VaadinAppointmentEditView extends VaadinBaseEditView<Appointment> implements AppointmentEditView {

    private @PropertyId("name") MTextField textFieldRemark;
    private @PropertyId("fromDate") DateTimeAndLocalTimeField dateTimeAndLocalTimeFieldFrom;
    private @PropertyId("toDate") DateTimeAndLocalTimeField dateTimeAndLocalTimeFieldTo;

    @Override
    public void init() {
        initComponents(Appointment.class);
    }

    @Override
    protected void initComponents(Class<Appointment> clazz) {
        final Property.ValueChangeListener valueChangeListener = new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                final Property property = event.getProperty();
                if (property != null) {
                    try {
                        final Object val = property instanceof AbstractField ? ((AbstractField) property).getConverter() == null ? property.getValue() : ((AbstractField) property).getConvertedValue() : property.getValue();
                        getValueChangedHandler().handleChanged(VaadinUiUtil.detectPropertyId (property), val);
                    } catch (Exception e) {
                        //log.error("error in value change", e);
                    }
                }
            }
        };

        textFieldRemark = getVaadinComponentFactory().createTextFieldWithLabel("name", valueChangeListener, true);

        super.initComponents(clazz);
        getFormLayout().with(textFieldRemark);
        withStyleName(getName());
    }

    @Override
    public String getName() {
        return AppointmentEditView.VIEW_NAME;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }


}
