package com.example.userauth.Controller;

import com.example.userauth.API.ApiResponse;
import com.example.userauth.DTOs.BookingDtos.BookingCreationDto;
import com.example.userauth.DTOs.BookingDtos.BookingResponseDto;
import com.example.userauth.DTOs.PaymentDetailsDto;
import com.example.userauth.Services.Booking.BookingServices;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/customer")
@AllArgsConstructor
public class BookingController extends BaseController {
    private final BookingServices bookingServices;

//    @PostMapping("/book")
//    @PreAuthorize("hasAuthority('Role_User')")
//
//    public ResponseEntity<ApiResponse> bookShow(@RequestBody BookingCreationDto bookingCreationDto){
//        BookingResponseDto bookingResponseDto = bookingServices.bookShow(bookingCreationDto);
//        return null;
//    }
//
    @PostMapping("/booking/show/{showId}")
    @PreAuthorize("hasAuthority('Role_User')")
    public ResponseEntity<ApiResponse> bookShow( @PathVariable Long showId ,@RequestBody BookingCreationDto BookingCreationDto){
        BookingResponseDto bookingResponseDto = bookingServices.bookShow(showId , BookingCreationDto );
        return ResponseEntity.ok(successResponse("BOOKING CONFIRMED" , true , bookingResponseDto));
    }
    @PostMapping("/finalize/booking/{bookingId}")
    @PreAuthorize("hasAuthority('Role_User')")
    public  ResponseEntity<ApiResponse> finalizeBooking(@PathVariable Long bookingId , PaymentDetailsDto paymentDetailsDto){
       BookingResponseDto bookingResponseDto =  bookingServices.finalizeBooking(bookingId, paymentDetailsDto);
       return ResponseEntity.ok(successResponse("Successfully Booked" , true , bookingResponseDto));
    }
}
