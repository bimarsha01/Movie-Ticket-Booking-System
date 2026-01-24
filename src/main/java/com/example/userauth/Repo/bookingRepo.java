package com.example.userauth.Repo;

import com.example.userauth.Models.Booking;
import com.example.userauth.Models.Enums.EStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface bookingRepo  extends JpaRepository<Booking, Long> {
    List<Booking> findAllByeStatusAndBookingTimeBefore(EStatus eStatus, LocalDateTime time);

}
