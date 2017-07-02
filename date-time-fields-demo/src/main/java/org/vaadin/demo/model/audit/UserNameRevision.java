package org.vaadin.demo.model.audit;

import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

import javax.persistence.Entity;

/**
 * Created by huth on 30.06.2017.
 */
@Entity
@RevisionEntity(UserNameRevisionListener.class)
public class UserNameRevision extends DefaultRevisionEntity {
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
