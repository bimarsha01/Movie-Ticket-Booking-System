package com.example.userauth.Services.Booking;

import com.example.userauth.DTOs.BookingDtos.BookingCreationDto;
import com.example.userauth.DTOs.BookingDtos.BookingResponseDto;
import com.example.userauth.ExceptionHandling.AlreadyExistException;
import com.example.userauth.ExceptionHandling.NotFoundException;
import com.example.userauth.ExceptionHandling.UnauthorizedException;
import com.example.userauth.Mapper.BookingMapper;
import com.example.userauth.Models.*;
import com.example.userauth.Models.Enums.EPayment;
import com.example.userauth.Models.Enums.EStatus;
import com.example.userauth.Repo.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Slf4j
@Service
@RequiredArgsConstructor
public class BookingServices {

    private final showRepo showRepo;
    private final screensRepo screensRepo;
    private final showSeatRepo showSeatRepo;
    private final UserRepo userRepo;
    private final bookingRepo bookingRepo;
    private final BookingMapper bookingMapper;


    public BookingResponseDto bookShow(Long showId, BookingCreationDto bookingCreationDto) {
        log.info("show id is {}",showId);

        String Username = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("username is {}" , Username);
        User user = userRepo.findByUsername(Username).orElseThrow(()-> new NotFoundException("NOT_FOUND" , "USER NOT FOUND"));
        log.info("User is loaded {}",user);
        log.info("Requesting IDs: {} of type {}",
                bookingCreationDto.getShowSeatIds(),
                bookingCreationDto.getShowSeatIds().getFirst().getClass().getSimpleName());
        List<ShowSeat> seats = showSeatRepo.findAllByIdWithLock(bookingCreationDto.getShowSeatIds());
        log.info("The ids are {}",seats);
        List<String> rebooking = new ArrayList<>();
        Show show = showRepo.findById(showId)
                .orElseThrow(() -> new NotFoundException("SHOW_NOT_FOUND", "Show not found"));

        for (ShowSeat showSeat : seats) {
            Long actualShowId = showSeat.getShow().getId();
           log.info("Actual show id is : {}",actualShowId);

            if (!actualShowId.equals(showId)) {
                log.info("Error thrown");
                throw new UnauthorizedException("UNAUTHORIZED", "You are trying to book the seats of different show");
            }

            if (showSeat.isReserved()) {
                rebooking.add("The seat : " + showSeat.getSeat().getRowNo() + " " + showSeat.getSeat().getSeatNo() + " were taken");
            }
        }
        if (!rebooking.isEmpty()) {
            throw new RuntimeException("The following seats were just taken: " + String.join(", ", rebooking));
        }

        Booking booking = new Booking();
        booking.setShow(show);
        booking.setUser(user);
        booking.setBookingTime(LocalDateTime.now());
        double totalPrice = seats.stream()
                .filter(seat -> seat != null && seat.getShow() != null)
                .mapToDouble(seat -> seat.getShow().getPrice())
                .sum();
        booking.setTotalPrice(totalPrice);
        booking.setEStatus(EStatus.Successful);
        booking.setPaymentMethod(EPayment.Credit_card);


        for (ShowSeat showSeat : seats) {
            showSeat.setReserved(true);
            showSeat.setBooking(booking);
        }

        showSeatRepo.saveAll(seats);

        bookingRepo.save(booking);

        return bookingMapper.toDto(booking);
    }


}

