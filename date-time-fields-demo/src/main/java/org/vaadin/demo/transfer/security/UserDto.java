package org.vaadin.demo.transfer.security;

import lombok.Getter;
import lombok.Setter;

public class UserDto {

    @Getter @Setter private Long id;
    @Getter @Setter private String name;
    @Getter @Setter private String preName;
    @Getter @Setter private String email;
    @Getter @Setter private String username;
    @Getter @Setter private String password;

    // groups
    @Getter @Setter private Long group_id;
    @Getter @Setter private String group_name;
}
