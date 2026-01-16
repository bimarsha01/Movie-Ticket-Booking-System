package com.example.userauth.Mapper;

import com.example.userauth.DTOs.ScreenDtos.ScreenCreationDto;
import com.example.userauth.DTOs.ScreenDtos.ScreenResponseDto;
import com.example.userauth.Models.Screens;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ScreenMapper {
    @Mapping(target = "id" , ignore = true)
    @Mapping(target = "theatre" , ignore = true)
    @Mapping(target = "shows" , ignore = true)
    @Mapping(target = "seats" , ignore = true)
    Screens toEntity(ScreenCreationDto dto);

    ScreenResponseDto toDto(Screens screens);


}
