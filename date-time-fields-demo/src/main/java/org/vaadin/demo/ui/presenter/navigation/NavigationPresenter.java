package org.vaadin.demo.ui.presenter.navigation;

import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.demo.model.NavigationItem;
import org.vaadin.demo.repositories.NavigationItemRepository;
import org.vaadin.demo.ui.view.navigation.NavigationView;
import org.vaadin.mvp.base.presenter.BasePresenter;
import org.vaadin.mvp.event.NavigationEvent;
import org.vaadin.mvp.handler.NavigationHandler;

import java.util.List;
import java.util.Map;

/**
 * Created by huth on 14.06.2017.
 */
public abstract class NavigationPresenter<V extends NavigationView> extends BasePresenter<V> {

    @Autowired
    NavigationItemRepository navigationItemRepository;

    @Override
    public void init() {
        super.init();
        getView().setNavigationHandler(new NavigationHandler() {
            @Override
            public void navigateTo(String springViewName) {
                getEventBus().publish(new NavigationEvent<String>(springViewName, String.class));
            }
        });
        final Map<NavigationItem, List<NavigationItem> navigationItemListMap = Maps.newTreeMap();
        final List<NavigationItem> navigationItems = navigationItemRepository.findAll();
        if (CollectionUtils.isNotEmpty(navigationItems)) {

        }

        // getView().initNavigation();
    }

    public NavigationPresenter(V view) {
        super(view);
    }
}
