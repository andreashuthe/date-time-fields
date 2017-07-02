package org.vaadin.mvp.edit.view;

import org.vaadin.mvp.base.view.View;
import org.vaadin.mvp.handler.CreateHandler;
import org.vaadin.mvp.handler.LoadHandler;
import org.vaadin.mvp.handler.SaveHandler;
import org.vaadin.mvp.handler.ValueChangedHandler;

/**
 * Created by huth on 09.06.2017.
 */
public interface EditView<ENTITY> extends View {

    void setSaveHandler (final SaveHandler entitySaveHandler);
    void setLoadHandler (final LoadHandler entityLoadHandler);
    void setCreateHandler (final CreateHandler entityCreateHandler);
    void setValueChangedHandler (final ValueChangedHandler valueChangedHandler);

    SaveHandler getSaveHandler ();
    LoadHandler getLoadHandler ();
    CreateHandler getCreateHandler ();
    ValueChangedHandler getValueChangedHandler();

    void load(ENTITY entity);

}
