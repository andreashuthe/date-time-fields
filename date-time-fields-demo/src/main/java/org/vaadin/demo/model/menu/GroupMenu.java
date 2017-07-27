package org.vaadin.demo.model.menu;

import lombok.Getter;
import lombok.Setter;
import org.vaadin.demo.model.security.Group;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by huth on 07.07.2017.
 */
@Entity
@Table(name = "groups_menu")
public class GroupMenu implements java.io.Serializable {

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Getter @Setter private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @Getter @Setter private Group groups;
    @ManyToOne(fetch = FetchType.EAGER)
    @Getter @Setter private MenuModel menuModel;
    @Getter @Setter private Boolean activ; // decides if the menu is active for the group
}
