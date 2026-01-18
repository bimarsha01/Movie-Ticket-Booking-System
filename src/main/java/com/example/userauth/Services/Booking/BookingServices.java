package com.example.userauth.Services.Booking;

import com.example.userauth.DTOs.BookingDtos.BookingCreationDto;
import com.example.userauth.DTOs.BookingDtos.BookingResponseDto;
import com.example.userauth.ExceptionHandling.NotFoundException;
import com.example.userauth.Models.Screens;
import com.example.userauth.Models.Show;
import com.example.userauth.Repo.screensRepo;
import com.example.userauth.Repo.showRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Transactional
@Slf4j
@Service
@RequiredArgsConstructor
public class BookingServices {

    private final showRepo showRepo;
    private final screensRepo screensRepo;

    public BookingResponseDto bookShow(BookingCreationDto bookingCreationDto) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Show show = showRepo.findById(bookingCreationDto.getShowId()).orElseThrow(()->new NotFoundException("NOT_FOUND" , "SHOW WITH ID "+ bookingCreationDto.getShowId() + " not found"));

        return null;

    }
}
