package org.vaadin.mvp.util;

import com.vaadin.data.Property;
import com.vaadin.data.util.converter.Converter;
import org.vaadin.components.datetimefields.DateTimeAndLocalTimeField;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.fields.MPasswordField;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.layouts.MFormLayout;
import org.vaadin.viritin.layouts.MHorizontalLayout;

/**
 * Created by huth on 09.06.2017.
 */
public interface IVaadinComponentFactory {

    MButton createCommitButton();

    MHorizontalLayout createCommitButtonBar();

    MFormLayout createFormLayout();

    MTextField createTextFieldWithLabel(String id);

    MTextField createTextFieldWithLabel(String id, boolean required);

    MPasswordField createPasswordField(String id, boolean required);

    MTextField createTextFieldWithLabel(String id, Property.ValueChangeListener valueChangeListener);

    MTextField createTextFieldWithLabel(String id, Property.ValueChangeListener valueChangeListener, boolean required);

    MTextField createTextFieldWithLabel(String id, Property.ValueChangeListener valueChangeListener, boolean required, Converter<String, ?> converter);

    DateTimeAndLocalTimeField createDateTimeAndLocalTimeField(String id, Property.ValueChangeListener valueChangeListener, boolean required);
}
