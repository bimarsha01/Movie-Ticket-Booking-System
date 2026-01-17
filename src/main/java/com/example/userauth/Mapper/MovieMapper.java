package com.example.userauth.Mapper;

import com.example.userauth.DTOs.MovieDtos.MovieCreationDto;
import com.example.userauth.DTOs.MovieDtos.MovieResponseDto;
import com.example.userauth.Models.Movies;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    @Mapping(target = "id" , ignore = true)
    @Mapping(target = "shows" , ignore = true)
    Movies toEntity(MovieCreationDto dto);

    List<Movies> toEntityList(List<MovieCreationDto> dtos);

MovieResponseDto toDto(Movies movies);

List<MovieResponseDto> toDtoList(List<Movies> moviesList);

}
