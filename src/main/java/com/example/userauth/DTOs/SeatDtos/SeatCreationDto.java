package com.example.userauth.DTOs.SeatDtos;


import com.example.userauth.ExceptionHandling.fieldErrorConstant;
import com.example.userauth.Models.Screens;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class SeatCreationDto {

        @NotNull(message = fieldErrorConstant.NOT_NULL)
        @Positive
        private Integer seatsPerRow;

        @NotNull(message = fieldErrorConstant.NOT_NULL)
        @Positive
        private Integer numberOfRows;

        @NotNull(message = fieldErrorConstant.NOT_NULL)
        private Long screenId;
    }

