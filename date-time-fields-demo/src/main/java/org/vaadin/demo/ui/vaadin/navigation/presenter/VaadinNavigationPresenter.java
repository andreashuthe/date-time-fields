package org.vaadin.demo.ui.vaadin.navigation.presenter;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.demo.ui.presenter.navigation.NavigationPresenter;
import org.vaadin.demo.ui.vaadin.navigation.view.VaadinNavigationView;

/**
 * Created by huth on 16.06.2017.
 */
@UIScope
@SpringComponent
public class VaadinNavigationPresenter extends NavigationPresenter<VaadinNavigationView> {
    @Autowired
    public VaadinNavigationPresenter(VaadinNavigationView view) {
        super(view);
    }
}
