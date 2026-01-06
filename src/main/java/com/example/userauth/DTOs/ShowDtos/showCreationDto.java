package com.example.userauth.DTOs.ShowDtos;


import com.example.userauth.Models.Movies;
import com.example.userauth.Models.Screens;
import com.example.userauth.Models.Theatre;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class showCreationDto {

    @NotNull(message = "Start time is required")
    @Future(message = "Show must start in the future")
    private LocalDateTime startTime;


    private LocalDateTime endTime;

    @Positive(message = "Must be a greater than 0")
    private Double price;

    @NotNull(message = "Movie id is required")
    private Long movieId;

    @NotNull(message = "Screen id is required")
    private Long screenId;
}
