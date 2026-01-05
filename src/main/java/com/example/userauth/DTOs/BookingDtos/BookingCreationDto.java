package com.example.userauth.DTOs.BookingDtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingCreationDto {
    @Getter @Setter
    public class BookingRequestDto {

        private Long showId;

        private List<Long> showSeatIds;
    }
}
