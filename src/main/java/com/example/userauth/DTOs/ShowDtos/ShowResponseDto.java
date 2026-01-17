package com.example.userauth.DTOs.ShowDtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShowResponseDto {

    private Long id;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Double price;

    private String movieName;

    private String screenNo;

    private Set<String> genre;
}
