package org.vaadin.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vaadin.demo.model.NavigationItem;

/**
 * Created by andreas_h on 05.06.17.
 */
public interface NavigationItemRepository extends JpaRepository<NavigationItem, Long> {
}
