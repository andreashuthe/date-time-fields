package org.vaadin.mvp.util;

import com.vaadin.data.Property;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HasComponents;
import org.vaadin.mvp.base.view.View;

/**
 * Created by huth on 09.06.2017.
 */
public class VaadinUiUtil {

    public static String detectPropertyId(final Property property) {
        if (property instanceof AbstractComponent) {
            return ((AbstractComponent) property).getId();
        } else {
            return null;
        }
    }

    public static Component findComponentsWithId(HasComponents hasComponents, String id){
        for(Component child : hasComponents) {
            if(id.equals(child.getId())) {
                // found it!
                return child;
            } else if(child instanceof HasComponents && !(child instanceof View)) {
                // recursively go through all children that themselves have children
                final Component ret= findComponentsWithId((HasComponents) child, id);
                if(ret!=null) {
                    return ret;
                }
            }
        }
        // none was found
        return null;
    }
}
