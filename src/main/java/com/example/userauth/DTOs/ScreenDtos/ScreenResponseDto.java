package com.example.userauth.DTOs.ScreenDtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScreenResponseDto {

    private Long id;

    private Integer screenNo;

    private Long theatreId;

    private Integer totalRows;

    private Integer seatsPerRow;
}
