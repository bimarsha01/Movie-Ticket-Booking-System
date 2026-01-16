package com.example.userauth.Mapper;

import com.example.userauth.DTOs.AdminRequestDto;
import com.example.userauth.Models.AdminRequestForTheatre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdminRequestMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user" , ignore = true)
    AdminRequestForTheatre toEntity(AdminRequestDto dto);

    @Mapping(source = "user.userId", target = "userId")
    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "status", target = "status")
    AdminRequestDto toDto(AdminRequestForTheatre entity);

    List<AdminRequestDto> toDtoList(List<AdminRequestForTheatre> adminRequestForTheatreList);
}