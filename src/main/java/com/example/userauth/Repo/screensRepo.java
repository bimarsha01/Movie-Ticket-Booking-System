package com.example.userauth.Repo;

import com.example.userauth.Models.Movies;
import com.example.userauth.Models.Screens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface screensRepo  extends JpaRepository<Screens, Long> {
}
