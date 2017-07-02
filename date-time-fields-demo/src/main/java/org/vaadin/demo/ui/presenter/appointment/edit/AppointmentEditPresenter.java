package org.vaadin.demo.ui.presenter.appointment.edit;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.demo.model.Appointment;
import org.vaadin.demo.repositories.AppointmentRepository;
import org.vaadin.demo.ui.view.appointment.edit.AppointmentEditView;
import org.vaadin.mvp.edit.presenter.BaseEditPresenter;
import org.vaadin.mvp.handler.CreateHandler;
import org.vaadin.mvp.handler.LoadHandler;
import org.vaadin.mvp.handler.SaveHandler;
import org.vaadin.mvp.handler.ValueChangedHandler;

/**
 * Created by huth on 09.06.2017.
 */
public abstract class AppointmentEditPresenter<V extends AppointmentEditView> extends BaseEditPresenter<V, Appointment>  {

    @Autowired AppointmentRepository appointmentRepository;

    protected AppointmentEditPresenter(V view) {
        super(view);
    }

    @Override
    protected LoadHandler getLoadHandler() {
        return new LoadHandler() {
            @Override
            public void load() {
                getView().load(getDto());
            }
        };
    }

    @Override
    protected SaveHandler getSaveHandler() {
        return new SaveHandler() {
            @Override
            public void save() {
                saveEntity(getDto());
            }
        };
    }

    @Override
    protected CreateHandler getCreateHandler() {
        return new CreateHandler() {
            @Override
            public void create() {
                setDto(new Appointment());
                getView().load(getDto());
            }
        };
    }

    @Override
    protected ValueChangedHandler getValueChangedHandler() {
        return new ValueChangedHandler() {
            public void handleChanged(String propertyId, Object value) {
                applyValueChanged(propertyId, value);
            }
        };
    }


    @Override
    protected Appointment savaData() {
        return appointmentRepository.save(getDto());
    }

    @Override
    protected void fireEntitySavedEvent(Appointment appointment) {
        setDto(appointment);
        getLoadHandler().load();
    }

    @Override
    protected void fireEntityLoadedEvent() {

        getView().load(getDto());
    }
}
