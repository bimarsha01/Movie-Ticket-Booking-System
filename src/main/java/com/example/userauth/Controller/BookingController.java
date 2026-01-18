package com.example.userauth.Controller;

import com.example.userauth.API.ApiResponse;
import com.example.userauth.DTOs.BookingDtos.BookingCreationDto;
import com.example.userauth.DTOs.BookingDtos.BookingResponseDto;
import com.example.userauth.Services.Booking.BookingServices;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/customer")
@AllArgsConstructor
public class BookingController {
    private final BookingServices bookingServices;

    @PostMapping("/book")
    @PreAuthorize("hasAuthority('Role_User')")

    public ResponseEntity<ApiResponse> bookShow(@RequestBody BookingCreationDto bookingCreationDto){
        BookingResponseDto bookingResponseDto = bookingServices.bookShow(bookingCreationDto);
        return null;
    }
}
