package org.vaadin.demo.model.audit;

import org.hibernate.envers.RevisionListener;

/**
 * Created by huth on 30.06.2017.
 */
public class UserNameRevisionListener implements RevisionListener {

        @Override
        public void newRevision(Object revisionEntity) {
            final UserNameRevision rev = (UserNameRevision) revisionEntity;
            rev.setUserName("/* callcontext * /");
        }
}
