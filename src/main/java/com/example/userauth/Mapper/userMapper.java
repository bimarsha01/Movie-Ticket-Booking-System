package com.example.userauth.Mapper;

import com.example.userauth.DTOs.UserDtos.UserCreationDto;
import com.example.userauth.DTOs.UserDtos.UserResponseDto;
import com.example.userauth.DTOs.UserDtos.UserUpdationDto;
import com.example.userauth.Models.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface userMapper {

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "password", ignore = true)
    User toEntity(UserCreationDto dto);

    @Mapping(target = "id", source = "userId")
    @Mapping(target = "roles", ignore = true)
    UserResponseDto toDto(User user);

    List<UserResponseDto> toDtoList(List<User> userList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "contact", ignore = true)
    void updateUserFromDto(UserUpdationDto dto, @MappingTarget User user);
}