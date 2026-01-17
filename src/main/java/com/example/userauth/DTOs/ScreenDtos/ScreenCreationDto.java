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

        private Integer screenNo;

        private Long theatreId;

        private Integer totalRows;

        private Integer seatsPerRow;
    }

