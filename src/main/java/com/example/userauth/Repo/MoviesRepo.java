package com.example.userauth.Repo;

import com.example.userauth.Models.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoviesRepo extends JpaRepository<Movies , Long> {
    boolean getMoviesByTitle(String title);


    boolean existsByTitle(String title);

    Movies getMoviesById(Long id);
}
