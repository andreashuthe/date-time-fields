package org.vaadin.mvp.base.presenter;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.util.Assert;
import org.vaadin.eventbus.SpringEventBus;
import org.vaadin.mvp.base.view.View;

/**
 * Created by huth on 08.06.2017.
 */
public abstract class BasePresenter<T extends View> implements Presenter<T> {

    @Autowired @Qualifier(value = "eventBus") @Getter private SpringEventBus eventBus;

    private final @Getter T view;

    protected BasePresenter(T view) {
        Assert.notNull(view);
        this.view = view;
    }

    protected void init(){
        getView().init();
    }
}
