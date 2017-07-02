package org.vaadin.demo.ui.vaadin.navigation.view;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.MenuBar;
import de.steinwedel.messagebox.MessageBox;
import lombok.Getter;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.vaadin.demo.model.NavigationItem;
import org.vaadin.demo.ui.vaadin.VaadinView;
import org.vaadin.demo.ui.view.navigation.NavigationView;
import org.vaadin.mvp.handler.NavigationHandler;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.List;

/**
 * Created by huth on 16.06.2017.
 */
@UIScope
@SpringView(name = NavigationView.VIEW_NAME)
public class VaadinNavigationView extends MVerticalLayout implements NavigationView, VaadinView {
    @Getter private NavigationHandler navigationHandler;

    private MenuBar menuBar;

    private MenuBar.MenuItem previous = null;

    @Override
    public void init() {
        final CssLayout navigation = new CssLayout();
        addComponent(navigation);

        this.menuBar = new MenuBar();
        menuBar.setAutoOpen(true);
        navigation.addComponent(menuBar);
    }

    @Override
    public String getName() {
        return NavigationView.VIEW_NAME;
    }

    @Override
    public void setNavigationHandler(NavigationHandler navigationHandler) {
        this.navigationHandler = navigationHandler;
    }

    @Override
    public void initNavigation(List<NavigationItem> navigationItems) {
        if (CollectionUtils.isEmpty(navigationItems)){
            MessageBox.createError().asModal(true).withData("No navigationItems found");
        } else {
            for (final NavigationItem navigationItem : navigationItems) {
                addItem(navigationItem.getName(), navigationItem.getSpringViewName(), navigationItem.getParentId()== null ? null : navigationItem.getParentId() );
            }
        }
    }

    private void addItem(final String caption,
                        final String viewName,
                        final Long parentId) {

        final boolean addCommand = StringUtils.isNotBlank(viewName) && getNavigationHandler() != null;
        final MenuBar.Command menuCommand;
        if (addCommand) {
            menuCommand = new MenuBar.Command() {
                @Override
                public void menuSelected(final MenuBar.MenuItem selectedItem) {
                    getNavigationHandler().navigateTo(viewName);
                    if (previous != null) {
                        previous.setStyleName(null);
                    }
                    selectedItem.setStyleName("checked");
                    previous = selectedItem;
                }
            };
        } else {
            menuCommand = null;
        }

        MenuBar.MenuItem menuItem = null;
        if (parentId != null) {
            for (final MenuBar.MenuItem item : menuBar.getItems()) {
                if (item.getId() == (parentId.intValue())) {
                    menuItem = item;
                    break;
                }
            }
        }
        if (menuItem == null) {
            menuItem = menuBar.addItem(caption, menuCommand);
            menuItem.setCheckable(false);
        } else {
            menuItem.addItem(caption, menuCommand).setCheckable(false);
        }
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
