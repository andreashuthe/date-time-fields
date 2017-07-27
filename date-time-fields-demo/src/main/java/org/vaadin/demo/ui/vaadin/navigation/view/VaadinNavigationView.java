package org.vaadin.demo.ui.vaadin.navigation.view;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.MenuBar;
import de.steinwedel.messagebox.MessageBox;
import lombok.Getter;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.vaadin.demo.model.NavigationItem;
import org.vaadin.demo.model.menu.GroupMenu;
import org.vaadin.demo.model.menu.MenuModel;
import org.vaadin.demo.model.tree.GenericTreeNode;
import org.vaadin.demo.ui.vaadin.VaadinView;
import org.vaadin.demo.ui.view.navigation.NavigationView;
import org.vaadin.mvp.handler.NavigationHandler;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.Iterator;
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
    public void initMenu(GenericTreeNode<MenuModel> groupMenus) {
        for (final GenericTreeNode<MenuModel> genericTreeNode : groupMenus.getChildren()) {
            addMenuItem(null, genericTreeNode);
        }
    }

    private MenuBar.MenuItem addMenuItem(MenuBar.MenuItem parent, final GenericTreeNode<MenuModel> menuModelGenericTreeNode) {
        final MenuBar.MenuItem result;
        if (parent == null) {
            result = menuBar.addItem(menuModelGenericTreeNode.getData().getLabel(), null);
        } else {
            //final MenuBar.MenuItem parentItem = menuBar.addItem()
            final boolean addCommand = StringUtils.isNotBlank(menuModelGenericTreeNode.getData().getClassString()) && CollectionUtils.isEmpty(menuModelGenericTreeNode.getChildren());
            if (addCommand) {
                final MenuBar.Command menuCommand = new MenuBar.Command() {
                    @Override
                    public void menuSelected(final MenuBar.MenuItem selectedItem) {
                        getNavigationHandler().navigateTo(menuModelGenericTreeNode.getData().getClassString());
                        if (previous != null) {
                            previous.setStyleName(null);
                        }
                        selectedItem.setStyleName("checked");
                        previous = selectedItem;
                    }
                };
                result = parent.addItem(menuModelGenericTreeNode.getData().getLabel(), menuCommand);
            } else {
                result = parent.addItem(menuModelGenericTreeNode.getData().getLabel(), null);
            }
        }

        if (CollectionUtils.isNotEmpty(menuModelGenericTreeNode.getChildren())) {
            for (GenericTreeNode<MenuModel> genericTreeNode : menuModelGenericTreeNode.getChildren()) {
                addMenuItem(result, genericTreeNode);
            }
        }
        return result;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
