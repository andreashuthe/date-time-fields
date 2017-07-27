package org.vaadin.demo.model.security;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by huth on 10.07.2017.
 */
@Entity
@Table(name = "users")
public class User implements Serializable, UserDetails {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @Getter @Setter private String name;
    @Getter @Setter private String preName;
    @Getter @Setter private String email;
    @Getter @Setter private String username;
    @Getter @Setter private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @Getter @Setter private Group groups;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Lists.newArrayList(new SimpleGrantedAuthority("admin"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
