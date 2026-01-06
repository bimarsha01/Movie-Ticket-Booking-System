package com.example.userauth.DTOs.ScreenDtos;


import com.example.userauth.Models.Seat;
import com.example.userauth.Models.Theatre;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScreenCreationDto {


        @NotNull(message = "Screen number is required")
        private Integer screenNo;

        @NotNull(message = "Theatre ID is required")
        private Long theatreId;

        @Min(value = 1, message = "Must have at least 1 row")
        private Integer totalRows;

        @Min(value = 1, message = "Must have at least 1 column")
        private Integer seatsPerRow;
    }

