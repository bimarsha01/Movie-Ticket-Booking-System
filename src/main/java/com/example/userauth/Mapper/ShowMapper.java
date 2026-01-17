package com.example.userauth.Mapper;

import com.example.userauth.DTOs.ShowDtos.ShowCreationDto;
import com.example.userauth.DTOs.ShowDtos.ShowResponseDto;
import com.example.userauth.Models.Show;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ShowMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "movies", ignore = true)
    @Mapping(target = "theatre", ignore = true)
    @Mapping(target = "screens", ignore = true)
    @Mapping(target = "endTime", ignore = true)
    Show toEntity(ShowCreationDto dto);

    @Mapping(source = "movies.title" , target = "movieName")
    @Mapping(source = "screens.screenNo" , target = "screenNo")
    @Mapping(source = "movies.genres" , target = "genre")
    ShowResponseDto toDto(Show show);


    List<ShowResponseDto> toDtoList(List<Show> saved);
}
//moviename , movie language , movie id , screenid , screenno;