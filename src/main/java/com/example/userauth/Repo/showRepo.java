package com.example.userauth.Repo;

import com.example.userauth.Helpers.MovieMinInfo;
import com.example.userauth.Models.Movies;
import com.example.userauth.Models.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface showRepo extends JpaRepository<Show , Long> {

    List<Show> findAllByEndTimeAfter(LocalDateTime now);

    @Query("SELECT DISTINCT s.movies.id as id, s.movies.title as title FROM Show s WHERE s.theatre.id = :theatreId")
    List<MovieMinInfo> findMinMovieInfoByTheatre(Long theatreId);

    Show findByMovies(Movies movies);

    List<Show> findByMoviesIdAndTheatreId(Long movies_id, Long theatre_id);
}

