package com.example.userauth.DTOs.ShowSeat;

import lombok.Data;

@Data
public class ShowSeatResponseDto {

    private Long id;
    private Integer seatNumber;
    private Integer rowNumber;
    private boolean isReserved;
    private double price;
}
