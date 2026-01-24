package com.example.userauth.Services;

import com.example.userauth.DTOs.WebSocketUpdate;
import com.example.userauth.Models.Booking;
import com.example.userauth.Models.Enums.EStatus;
import com.example.userauth.Models.ShowSeat;
import com.example.userauth.Repo.bookingRepo;
import com.example.userauth.Repo.showSeatRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import java.time.LocalDateTime;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class CheckBookingServices {

    private final bookingRepo bookingRepo;
    private final showSeatRepo showSeatRepo;
    private final SimpMessagingTemplate messagingTemplate;


    @Scheduled(cron = "0 * * * * *")
    @Transactional
    public void checkBooking() {

        LocalDateTime time = LocalDateTime.now().minusMinutes(5);

        List<Booking> expiredBookings = bookingRepo.findAllByeStatusAndBookingTimeBefore(EStatus.Pending, time);


        if (expiredBookings.isEmpty()) {
            return;
        }
        for (Booking booking : expiredBookings) {
            booking.setEStatus(EStatus.Expired);

            List<ShowSeat> formatBooking = showSeatRepo.findByBooking_Id(booking.getId());

            List<String> seatIds = formatBooking.stream().map(showSeat -> {
                char row = (char) (64 + showSeat.getSeat().getRowNo());
                return row + String.format("%02d", showSeat.getSeat().getSeatNo());
            }).toList();

            for(ShowSeat showSeat : formatBooking){
                showSeat.setReserved(false);
                showSeat.setBooking(null);
            }

            WebSocketUpdate message = new WebSocketUpdate("EXPIRED", seatIds);
            messagingTemplate.convertAndSend("/topic/show/" + booking.getShow().getId(), message);

            showSeatRepo.saveAll(formatBooking);


        }

        bookingRepo.saveAll(expiredBookings);

    }

}
