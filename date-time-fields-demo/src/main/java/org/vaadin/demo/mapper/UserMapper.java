package org.vaadin.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.vaadin.demo.model.security.User;
import org.vaadin.demo.transfer.security.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    @Mappings({
            @Mapping(source = "groups.id", target = "group_id", ignore = true),
            @Mapping(source = "groups.name", target = "group_name", ignore = true)

    })
    UserDto userToUserDto(User user);

    @Mappings({
            @Mapping(source = "group_id", target = "groups.id"),
            @Mapping(source = "group_name", target = "groups.name")
    })
    User userDtoToUser(UserDto userDto);
}
