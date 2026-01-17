package com.example.userauth.DTOs.ScreenDtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ScreenResponseDto {

    private Long id;

    private Integer screenNo;

    private Long theatreId;

    private Integer totalRows;

    private Integer seatsPerRow;

    private Integer totalSeats;
}
