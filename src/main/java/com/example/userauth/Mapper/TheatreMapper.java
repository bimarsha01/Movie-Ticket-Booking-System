package com.example.userauth.Mapper;

import com.example.userauth.DTOs.TheatreDtos.TheatreCreationDto;
import com.example.userauth.DTOs.TheatreDtos.TheatreResponseDto;
import com.example.userauth.Models.Theatre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TheatreMapper {
    @Mapping(target =  "id" , ignore = true)
    @Mapping(target = "user" , ignore = true)
    @Mapping(target = "screens" , ignore = true)
    @Mapping(target = "shows" , ignore = true)
    Theatre toEntity(TheatreCreationDto dto);


    @Mapping(source = "user.username" , target = "userOwnerName")
    @Mapping(target = "screensSet" , ignore = true)
    TheatreResponseDto toDto(Theatre theatre);

List<TheatreResponseDto> toDtoList(List<Theatre> theatreList);
}
