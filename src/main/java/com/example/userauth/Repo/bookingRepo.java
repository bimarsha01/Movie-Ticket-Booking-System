package com.example.userauth.Repo;

import com.example.userauth.Models.Booking;
import com.example.userauth.Models.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface bookingRepo  extends JpaRepository<Booking, Long> {
}
