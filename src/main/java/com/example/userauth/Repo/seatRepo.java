package com.example.userauth.Repo;

import com.example.userauth.Models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface seatRepo  extends JpaRepository<Seat, Long> {
    List<Seat> findByScreenId(Long attr0);

    List<Seat> findByScreen_Id(Long id);
}
