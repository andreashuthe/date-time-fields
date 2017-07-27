package org.vaadin.demo.ui.view.navigation;

import org.vaadin.demo.model.NavigationItem;
import org.vaadin.demo.model.menu.MenuModel;
import org.vaadin.demo.model.tree.GenericTreeNode;
import org.vaadin.mvp.base.view.View;
import org.vaadin.mvp.handler.NavigationHandler;

import java.util.List;

/**
 * Created by huth on 14.06.2017.
 */
public interface NavigationView extends View {

    String VIEW_NAME = "navigationView";

    void setNavigationHandler(NavigationHandler navigationHandler);

    void initMenu(GenericTreeNode<MenuModel> groupMenus);
}
