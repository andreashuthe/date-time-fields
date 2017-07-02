package org.vaadin.mvp.base.view.edit;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.navigator.View;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.mvp.edit.view.EditView;
import org.vaadin.mvp.handler.CreateHandler;
import org.vaadin.mvp.handler.DeleteHandler;
import org.vaadin.mvp.handler.I18nHandler;
import org.vaadin.mvp.handler.LoadHandler;
import org.vaadin.mvp.handler.SaveHandler;
import org.vaadin.mvp.handler.ValueChangedHandler;
import org.vaadin.mvp.util.IVaadinComponentFactory;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MFormLayout;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * Created by huth on 09.06.2017.
 */
public abstract class VaadinBaseEditView<ENTITY> extends MVerticalLayout implements EditView<ENTITY>, View {

    @Autowired @Getter IVaadinComponentFactory vaadinComponentFactory;

    //handler
    @Getter @Setter private SaveHandler saveHandler;
    @Getter @Setter private LoadHandler loadHandler;
    @Getter @Setter private CreateHandler createHandler;
    @Getter @Setter private DeleteHandler deleteHandler;
    @Getter @Setter private ValueChangedHandler valueChangedHandler;
    @Getter @Setter private I18nHandler i18NHandler;

    /**
     * UI Components
     */
    private MVerticalLayout messageLayout;
    @Getter(AccessLevel.PROTECTED) private MFormLayout formLayout;
    private MHorizontalLayout buttonBar;
    @Getter private MButton buttonSave;
    @Getter private MButton buttonNew;
    @Getter private MButton buttonDelete;
    private BeanFieldGroup<ENTITY> fieldGroup;


    protected void initComponents(Class<ENTITY> clazz) {
        fieldGroup = new BeanFieldGroup<ENTITY>(clazz);
        fieldGroup.buildAndBindMemberFields(this);
        buttonSave = vaadinComponentFactory.createCommitButton();
        buttonSave.addClickListener(new MButton.MClickListener() {
            public void onClick() {
                getSaveHandler().save();
            }
        });
        buttonNew = vaadinComponentFactory.createCommitButton();
        buttonNew.addClickListener(new MButton.MClickListener() {
            public void onClick() {
                getCreateHandler().create();
            }
        });
        buttonDelete = vaadinComponentFactory.createCommitButton();
        buttonNew.addClickListener(new MButton.MClickListener() {
            public void onClick() {
                getDeleteHandler().delete();
            }
        });
        buttonBar = vaadinComponentFactory.createCommitButtonBar().with(buttonNew, buttonSave);
        messageLayout = new MVerticalLayout().withMargin(false).withSizeUndefined();
        formLayout = vaadinComponentFactory.createFormLayout();
        formLayout.with(messageLayout);
        with(formLayout, buttonBar);
//        buttonBar.withAlign(formLayout, Alignment.BOTTOM_RIGHT);
    }

    public void load(ENTITY entity) {
        fieldGroup.setItemDataSource(entity);
    }


}
