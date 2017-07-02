package org.vaadin.demo.model;

import com.google.common.base.Objects;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.io.Serializable;

/**
 * Created by andreas_h on 05.06.17.
 */
@Entity
public class NavigationItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column (name="ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @Column (name="PARENT_ID")
    @Getter @Setter Long parentId;

    @Column (name="SPRINGVIEWNAME")
    @Getter @Setter private String springViewName;

    @Column (name="NAME")
    @Getter @Setter private String name;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof NavigationItem))
            return false;

        final NavigationItem other = (NavigationItem) obj;
        if (getId() == null) {
            if (other.getId() != null)
                return false;
        } else if (!getId().equals(other.getId()))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getClass().getName());
    }

}
