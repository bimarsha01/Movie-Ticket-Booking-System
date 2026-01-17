package com.example.userauth.DTOs.MovieDtos;

import com.example.userauth.Models.Enums.EGenre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieResponseDto {

    private Long id;
    private String title;
    private String description;
    private Integer duration;
    private Set<String> genres;
}
