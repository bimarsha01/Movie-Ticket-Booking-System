package com.example.userauth.Repo;

import com.example.userauth.Models.Movies;
import com.example.userauth.Models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface seatRepo  extends JpaRepository<Seat, Long> {
}
