package org.vaadin.components.datetimefields;

import org.joda.time.Interval;

import com.vaadin.data.Property;
import com.vaadin.data.util.converter.Converter.ConversionException;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import org.vaadin.listener.DateTimeShortCutListener;

import java.util.List;

@SuppressWarnings("serial")
public class IntervalField extends CustomField<Interval> implements IDateTimeField{

    private final DateField fromField;
    private final DateField toField;

    public IntervalField() {
        fromField = new DateField();
        toField = new DateField();
    }

    @Override
    protected Component initContent() {
        HorizontalLayout layout = new HorizontalLayout();

        layout.addComponent(fromField);
        layout.addComponent(new Label(" - "));
        layout.addComponent(toField);

        return layout;
    }

    @Override
    public Class<? extends Interval> getType() {
        return Interval.class;
    }

    @Override
    public void setValue(Interval interval) throws com.vaadin.data.Property.ReadOnlyException, ConversionException {
        super.setValue(interval);
        fromField.setValue(interval.getStart().toDate());
        fromField.setValue(interval.getEnd().toDate());
    }

    @Override
    public Interval getValue() {
        return new Interval(fromField.getValue().getTime(), toField.getValue().getTime());
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void setPropertyDataSource(Property newDataSource) {
        super.setPropertyDataSource(newDataSource);
        if (newDataSource != null) {
            Object newValue = newDataSource.getValue();
            if (newValue != null && newValue instanceof Interval) {
                Interval interval = (Interval) newValue;
                fromField.setValue(interval.getStart().toDate());
                toField.setValue(interval.getEnd().toDate());
            }
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public Property getPropertyDataSource() {
        Property result = super.getPropertyDataSource();

        if (result != null) {
            Object newValue = result.getValue();
            if (fromField.getValue() != null && toField.getValue() != null && newValue instanceof Interval) {
                Interval interval = new Interval(fromField.getValue().getTime(), toField.getValue().getTime());
                result.setValue(interval);
            }
        }

        return result;
    }

    @Override
    public void addDateTimeShortCutListener(List<DateTimeShortCutListener> shortCutListeners) {
        for (final DateTimeShortCutListener shortCutListener : shortCutListeners) {
            fromField.addShortcutListener(shortCutListener);
            toField.addShortcutListener(shortCutListener);
        }
    }

    @Override
    public void setReadOnly(boolean readOnly) {
        super.setReadOnly(readOnly);
        fromField.setReadOnly(readOnly);
        toField.setReadOnly(readOnly);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        fromField.setEnabled(enabled);
        toField.setEnabled(enabled);
    }
}
