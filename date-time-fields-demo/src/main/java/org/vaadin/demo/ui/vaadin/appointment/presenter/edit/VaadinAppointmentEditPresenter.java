package org.vaadin.demo.ui.vaadin.appointment.presenter.edit;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.demo.model.Appointment;
import org.vaadin.demo.ui.presenter.appointment.edit.AppointmentEditPresenter;
import org.vaadin.demo.ui.vaadin.appointment.view.edit.VaadinAppointmentEditView;

/**
 * Created by huth on 09.06.2017.
 */
@UIScope
@SpringComponent
public class VaadinAppointmentEditPresenter extends AppointmentEditPresenter<VaadinAppointmentEditView> {

    @Autowired
    public VaadinAppointmentEditPresenter(VaadinAppointmentEditView view) {
        super(view);
    }

    protected void onEntitySavedEvent(Appointment appointment) {
        setDto(appointment);
        getLoadHandler().load();
    }
}
