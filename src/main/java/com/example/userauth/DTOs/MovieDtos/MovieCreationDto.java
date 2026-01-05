package com.example.userauth.DTOs.MovieDtos;

import com.example.userauth.ExceptionHandling.fieldErrorConstant;
import com.example.userauth.Models.Enums.EGenre;
import com.example.userauth.Models.Show;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class MovieCreationDto {

    @NotBlank(message = fieldErrorConstant.NOT_BLANK)
    @NotNull(message = fieldErrorConstant.NOT_NULL)
    private String title;

    @NotBlank(message = fieldErrorConstant.NOT_BLANK)
    @NotNull(message = fieldErrorConstant.NOT_NULL)
    private String description;

    @NotNull(message = fieldErrorConstant.NOT_NULL)
    private Integer duration;

    @NotEmpty(message = fieldErrorConstant.NOT_EMPTY)
    private Set<EGenre> genres = new HashSet<>();


}
