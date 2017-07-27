package org.vaadin.demo.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.vaadin.demo.model.menu.GroupMenu;
import org.vaadin.demo.model.menu.MenuModel;

import java.util.List;
import java.util.Set;

/**
 * Created by andreas_h on 05.06.17.
 */
public interface MenuModelRepository extends JpaRepository<MenuModel, Long> {

    List<MenuModel> findByParentAndGroupMenusIn(Long parent, Set<GroupMenu> groupMenus, Sort sort);

    List<MenuModel> findByParentIsNullAndGroupMenusIn(Set<GroupMenu> groupMenus, Sort sort);

}
