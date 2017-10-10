package org.vaadin.demo.ui.presenter.navigation;

import com.google.gwt.thirdparty.guava.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.demo.event.LoginSuccessEvent;
import org.vaadin.demo.event.NavigationEvent;
import org.vaadin.demo.model.menu.GroupMenu;
import org.vaadin.demo.model.menu.MenuModel;
import org.vaadin.demo.model.tree.GenericTreeNode;
import org.vaadin.demo.repositories.GroupMenuRepository;
import org.vaadin.demo.repositories.MenuModelRepository;
import org.vaadin.demo.repositories.NavigationItemRepository;
import org.vaadin.demo.transfer.security.UserDto;
import org.vaadin.demo.ui.view.navigation.NavigationView;
import org.vaadin.mvp.base.presenter.BasePresenter;
import org.vaadin.mvp.handler.NavigationHandler;
import org.vaadin.spring.events.annotation.EventBusListenerMethod;

import java.util.List;
import java.util.Set;

/**
 * Created by huth on 14.06.2017.
 */
public abstract class NavigationPresenter<V extends NavigationView> extends BasePresenter<V> {

    @Autowired NavigationItemRepository navigationItemRepository;

    @Autowired GroupMenuRepository groupMenuRepository;
    @Autowired MenuModelRepository menuModelRepository;

    @Override
    public void init() {
        super.init();
        getView().setNavigationHandler(new NavigationHandler() {
            @Override
            public void navigateTo(String springViewName) {
                getEventBus().publish(this, new NavigationEvent(springViewName));
            }
        });
        initMenu();
    }


    private void initMenu(){
        final List<GroupMenu> groupMenus = groupMenuRepository.findByGroups_Id(1L, null);
        final Set<GroupMenu> groupMenuSet = Sets.newLinkedHashSet(groupMenus);


        final List<MenuModel> rootMenuModels = menuModelRepository.findByParentIsNullAndGroupMenusIn(groupMenuSet, null);

        final GenericTreeNode<MenuModel> menuTree = new GenericTreeNode<>();
        final MenuModel root = new MenuModel();
        root.setId(-1L);
        menuTree.setData(root);
        for (final MenuModel rootMenuModel : rootMenuModels) {
            appendChild(groupMenuSet, menuTree, rootMenuModel);
        }

        final GenericTreeNode<MenuModel> dummyChild = new GenericTreeNode<>();
        final MenuModel dummyMenuModel = new MenuModel();
        dummyMenuModel.setId(-20L);
        dummyMenuModel.setLabel("Dummy");
        dummyChild.setData(dummyMenuModel);

        final MenuModel dummyMenuModel1 = new MenuModel();
        dummyMenuModel1.setId(-20L);
        dummyMenuModel1.setLabel("Demo");
        dummyMenuModel1.setClassString("demoPresenter");
        dummyChild.addChild(dummyMenuModel1);
        menuTree.addChild(dummyChild);

        getView().initMenu(menuTree);
    }

    private void appendChild (Set<GroupMenu> groupMenuSet, final GenericTreeNode<MenuModel> menuModelGenericTreeNode, MenuModel child) {
        final GenericTreeNode<MenuModel> childMenuModelNode = new GenericTreeNode<>(child);
        menuModelGenericTreeNode.getChildren().add(childMenuModelNode);

        final List<MenuModel> menuModels = menuModelRepository.findByParentAndGroupMenusIn( child.getId(), groupMenuSet,null);
        if (CollectionUtils.isNotEmpty(menuModels)) {
            for (MenuModel menuModel : menuModels) {
                appendChild(groupMenuSet, childMenuModelNode, menuModel);
            }
        }

    }

    @EventBusListenerMethod
    public void onEvent(NavigationEvent navigationEvent) {
        navigate(navigationEvent.getValue());
    }

    @EventBusListenerMethod
    public void onEvent(LoginSuccessEvent loginSuccessEvent) {

        login(loginSuccessEvent.getValue());
    }

    protected abstract void login(UserDto userDto);


    protected abstract void navigate(String name);

    public NavigationPresenter(V view) {
        super(view);
    }
}
