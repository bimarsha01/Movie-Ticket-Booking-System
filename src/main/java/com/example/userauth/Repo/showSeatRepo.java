package com.example.userauth.Repo;

import com.example.userauth.Models.Movies;
import com.example.userauth.Models.Show;
import com.example.userauth.Models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface showSeatRepo  extends JpaRepository<ShowSeat, Long> {
    List<ShowSeat> findByShow_Id(Long showId);
}
