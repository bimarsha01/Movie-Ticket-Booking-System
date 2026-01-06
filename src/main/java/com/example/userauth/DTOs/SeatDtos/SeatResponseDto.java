package com.example.userauth.DTOs.SeatDtos;


import com.example.userauth.ExceptionHandling.fieldErrorConstant;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SeatResponseDto {


    private Integer seatsPerRow;
    private Integer numberOfRows;
    private Long screenId;
    private Long screenNo;

}
