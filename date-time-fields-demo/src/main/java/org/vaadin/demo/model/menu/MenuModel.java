package org.vaadin.demo.model.menu;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by huth on 07.07.2017.
 */

@Entity
@Table(name = "menu_model")
public class MenuModel implements java.io.Serializable {

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Getter @Setter private Long id;
    @Getter @Setter private String label;  // caption of menu to display
    @Getter @Setter private String classString; //full class name of the Component to display ej  "com.sample.Component"
    @Getter @Setter private Long parent; // the idmenu who is the parent
    @Getter @Setter private Integer indice;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "menuModel")
    @Getter @Setter private Set<GroupMenu> groupMenus = new HashSet<GroupMenu>(0);
}
