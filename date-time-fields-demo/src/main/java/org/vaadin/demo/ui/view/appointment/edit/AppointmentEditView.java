package org.vaadin.demo.ui.view.appointment.edit;

import org.vaadin.demo.model.Appointment;
import org.vaadin.mvp.edit.view.EditView;

/**
 * Created by huth on 09.06.2017.
 */
public interface AppointmentEditView extends EditView<Appointment> {
    String VIEW_NAME = "appointmentEditView";
}
