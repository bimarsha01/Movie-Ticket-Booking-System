package com.example.userauth.Repo;

import com.example.userauth.Models.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface showRepo extends JpaRepository<Show , Long> {


    List<Show> findAllByEndTimeAfter(LocalDateTime now);
}
