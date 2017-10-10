package org.vaadin.demo.ui.presenter.demo;

import org.vaadin.demo.ui.view.demo.DemoView;
import org.vaadin.mvp.base.presenter.BasePresenter;

public abstract class DemoPresenter<V extends DemoView> extends BasePresenter<V> {

    public static final String PRESENTER_NAME = "demoPresenter";

    public DemoPresenter(V view) {
        super(view);
    }

    @Override
    protected void init() {
        super.init();
    }
}
