package com.example.userauth.Mapper;

import com.example.userauth.DTOs.AdminRequestDto;
import com.example.userauth.Models.AdminRequestForTheatre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdminRequestMapper {

    @Mapping(target = "id", ignore = true)
    AdminRequestForTheatre toEntity(AdminRequestDto dto);


    AdminRequestDto toDto(AdminRequestForTheatre adminRequestForTheatre);

    List<AdminRequestDto> toDtoList(List<AdminRequestForTheatre> adminRequestForTheatreList);
}