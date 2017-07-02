package org.vaadin.mvp.edit.presenter;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.commons.beanutils.PropertyUtils;
import org.vaadin.mvp.base.presenter.BasePresenter;
import org.vaadin.mvp.edit.view.EditView;
import org.vaadin.mvp.handler.CreateHandler;
import org.vaadin.mvp.handler.LoadHandler;
import org.vaadin.mvp.handler.SaveHandler;
import org.vaadin.mvp.handler.ValueChangedHandler;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by huth on 09.06.2017.
 */
public abstract class BaseEditPresenter<T extends EditView, ENTITY> extends BasePresenter<T> {
    public BaseEditPresenter(T view) {
        super(view);
    }

    private @Getter @Setter ENTITY dto;

    protected void init(){
        super.init();
        getView().setLoadHandler(getLoadHandler());
        getView().setSaveHandler(getSaveHandler());
        getView().setCreateHandler(getCreateHandler());
        getView().setValueChangedHandler(getValueChangedHandler());
    }

    protected abstract LoadHandler getLoadHandler();
    protected abstract SaveHandler getSaveHandler();
    protected abstract CreateHandler getCreateHandler();
    protected abstract ValueChangedHandler getValueChangedHandler();

    protected void loadEntity(final ENTITY entity){
        setDto(entity);
        fireEntityLoadedEvent();
    }

    protected void saveEntity(final ENTITY entity){
        final ENTITY savedEntity = savaData();
        fireEntitySavedEvent(entity);
    }

    protected abstract ENTITY savaData();

    protected void applyValueChanged(String propertyId, Object value) {
        boolean error = false;
        try {
            PropertyUtils.setProperty(getDto(), propertyId, value);
        } catch (IllegalAccessException e) {
            //log.error(propertyId, e);
            error = true;
        } catch (InvocationTargetException e) {
            //log.error(propertyId, e);
            error = true;
        } catch ( NoSuchMethodException e) {
            //log.error(propertyId, e);
            error = true;
        }
    }

    protected abstract void fireEntitySavedEvent(ENTITY entity);

    protected abstract void fireEntityLoadedEvent();
}
