package org.vaadin.mvp.base.presenter;

import com.vaadin.navigator.View;

/**
 * Created by huth on 11.07.2017.
 */
public interface VaadinNavigatablePresenter <V> extends View {
    V getView();
}
