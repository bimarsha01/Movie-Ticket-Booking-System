package com.example.userauth.BookingServices;

import com.example.userauth.DTOs.BookingDtos.BookingResponseDto;
import com.example.userauth.Models.Booking;
import com.example.userauth.Repo.BookingRepo;
import com.example.userauth.Services.Booking.BookingServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class BookingServicesTest {

    @Mock
    private BookingRepo bookingRepos;


    @InjectMocks
    private BookingServices bookingServices;

    @Test
    void shouldReturnBookingWhenIdIsCorrect(){
        Booking fakebooking = new Booking();
        fakebooking.setId(100L);

        Mockito.when(bookingRepos.findById(100L)).thenReturn(Optional.of(fakebooking));

        BookingResponseDto result = bookingServices.getBookingById(100L);

        assertNotNull(result);
        assertEquals(100L , result.getBookingId());

        Mockito.verify(bookingRepos , Mockito.times(1)).findById(100L);

    }
}
