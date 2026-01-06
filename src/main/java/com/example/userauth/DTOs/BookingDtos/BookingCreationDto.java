package com.example.userauth.DTOs.BookingDtos;


import com.example.userauth.ExceptionHandling.fieldErrorConstant;
import jakarta.validation.constraints.NotBlank;
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

        @NotBlank(message = fieldErrorConstant.NOT_BLANK)
        private Long showId;

        @NotBlank(message = fieldErrorConstant.NOT_BLANK)
        private List<Long> showSeatIds;
    }

