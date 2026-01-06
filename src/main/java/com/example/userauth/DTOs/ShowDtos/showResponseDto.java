package com.example.userauth.DTOs.ShowDtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class showResponseDto {

    private Long id;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Double price;

    private String movieName;

    private String movieLanguage;

    private String ScreenNo;


    private Long movieId;

    private Long screenId;
}
