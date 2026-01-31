package com.example.userauth.Repo;

import com.example.userauth.Models.Screens;
import com.example.userauth.Models.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ScreensRepo extends JpaRepository<Screens, Long> {
    Screens findByShows(Set<Show> shows);
}
