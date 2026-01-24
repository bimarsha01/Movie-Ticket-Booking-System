package com.example.userauth.DTOs.BookingDtos;


import com.example.userauth.Models.Show;
import com.example.userauth.Models.ShowSeat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponseDto {

    private Long bookingId;
    private String movieTitle;
    private String theatreName;
    private String status;
    private String bookingTime;
    private List<String> seatNumbers;
    private Double totalPrice;
}