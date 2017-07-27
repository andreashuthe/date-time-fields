package org.vaadin.demo.model.security;

import lombok.Getter;
import lombok.Setter;
import org.vaadin.demo.model.menu.GroupMenu;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by huth on 07.07.2017.
 */
@Entity
@Table(name = "groups")
public class Group implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @Getter @Setter private String name;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "groups")
    private Set<GroupMenu> groupMenus = new HashSet<GroupMenu>(0);

}
