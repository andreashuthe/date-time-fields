package org.vaadin.mvp.base.presenter;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.vaadin.mvp.base.view.View;
import org.vaadin.spring.events.EventBus;

import javax.annotation.PreDestroy;

/**
 * Created by huth on 08.06.2017.
 */
public abstract class BasePresenter<T extends View> implements Presenter<T> {

    @Autowired @Getter private EventBus.UIEventBus eventBus;

    private final @Getter T view;

    protected BasePresenter(T view) {
        Assert.notNull(view);
        this.view = view;
    }

    protected void init(){
        getEventBus().subscribe(this);
        getView().init();
    }

    @PreDestroy
        // It's good manners to do this, even though we should be automatically unsubscribed
        // when the UI is garbage collected
    void destroy() {
        getEventBus().unsubscribe(this);
    }
}
