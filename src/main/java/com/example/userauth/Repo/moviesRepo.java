package com.example.userauth.Repo;

import com.example.userauth.Models.Movies;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface moviesRepo extends JpaRepository<Movies , Long> {
    boolean getMoviesByTitle(String title);


    boolean existsByTitle(String title);
}
