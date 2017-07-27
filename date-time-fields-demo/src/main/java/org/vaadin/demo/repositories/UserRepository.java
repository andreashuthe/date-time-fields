package org.vaadin.demo.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.vaadin.demo.model.menu.GroupMenu;
import org.vaadin.demo.model.security.User;

import java.util.List;

/**
 * Created by andreas_h on 05.06.17.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findFirstByUsername(String userName);

}
