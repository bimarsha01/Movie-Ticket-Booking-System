package com.example.userauth.Services.Movies;

import com.example.userauth.DTOs.MovieDtos.MovieCreationDto;
import com.example.userauth.DTOs.MovieDtos.MovieResponseDto;
import com.example.userauth.ExceptionHandling.AlreadyExistException;
import com.example.userauth.Mapper.MovieMapper;
import com.example.userauth.Models.Movies;
import com.example.userauth.Repo.moviesRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
@Slf4j
@RequiredArgsConstructor
public class MovieServices {

    private final MovieMapper movieMapper;
    private final moviesRepo moviesRepo;

    @Transactional
    public List<MovieResponseDto> addMovies(List<MovieCreationDto> dtos) {
        log.info("Starting  upload for {} movies", dtos.size());


        for (MovieCreationDto dto : dtos) {
            if (moviesRepo.existsByTitle(dto.getTitle())) {
                throw new AlreadyExistException("MOVIE_EXISTS",
                        "The movie '" + dto.getTitle() + "' is already in the library.");
            }
        }
        List<Movies> entities = movieMapper.toEntityList(dtos);
        List<Movies> savedMovies = moviesRepo.saveAll(entities);

        log.info("Successfully inserted {} movies to the database", savedMovies.size());

        return movieMapper.toDtoList(savedMovies);
    }
}
