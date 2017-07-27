package org.vaadin.demo.ui.vaadin.appointment.presenter.edit;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.demo.ui.presenter.appointment.edit.AppointmentEditPresenter;
import org.vaadin.demo.ui.vaadin.appointment.view.edit.VaadinAppointmentEditView;
import org.vaadin.mvp.base.presenter.VaadinNavigatablePresenter;

/**
 * Created by huth on 09.06.2017.
 */
@UIScope
@SpringView(name = AppointmentEditPresenter.PRESENTER_NAME)
public class VaadinAppointmentEditPresenter extends AppointmentEditPresenter<VaadinAppointmentEditView> implements VaadinNavigatablePresenter<VaadinAppointmentEditView> {

    @Autowired
    public VaadinAppointmentEditPresenter(VaadinAppointmentEditView view) {
        super(view);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        init();
    }
}
