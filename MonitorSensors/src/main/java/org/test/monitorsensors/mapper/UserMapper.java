package org.test.monitorsensors.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.test.monitorsensors.dto.UserRequestDto;
import org.test.monitorsensors.dto.UserResponseDto;
import org.test.monitorsensors.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDto toDto(User user);
    @Mapping(target = "id", ignore = true)
    User toEntity(UserRequestDto userRequestDto);
    @Mapping(target = "id", ignore = true)
    User toUpdate(@MappingTarget User user, UserRequestDto userRequestDto);
}
