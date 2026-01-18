package com.example.userauth.Services.Theatre;

import com.example.userauth.DTOs.MovieDtos.MovieResponseDto;
import com.example.userauth.DTOs.ShowDtos.ShowResponseDto;
import com.example.userauth.DTOs.TheatreDtos.TheatreResponseDto;
import com.example.userauth.Helpers.MovieMinInfo;
import com.example.userauth.Mapper.MovieMapper;
import com.example.userauth.Mapper.ShowMapper;
import com.example.userauth.Mapper.TheatreMapper;
import com.example.userauth.Models.*;
import com.example.userauth.Repo.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
@Slf4j
@RequiredArgsConstructor
public class TheatreServices {
    private final theatreRepo theatreRepo;
    private final TheatreMapper theatreMapper;
    private final showRepo showRepo;
    private final moviesRepo moviesRepo;
    private final MovieMapper movieMapper;
    private final ShowMapper showMapper;
    private final showSeatRepo showSeatRepo;
    private final seatRepo seatRepo;

    public List<TheatreResponseDto> showAllTheatre() {
        List<Theatre> theatre = theatreRepo.findAll();

        return theatreMapper.toDtoList(theatre);

    }

    public List<MovieMinInfo> getMoviesOfEachTheatre(Long theatreId) {

        List<MovieMinInfo> movies = showRepo.findMinMovieInfoByTheatre(theatreId);

        return movies;
    }

    public List<ShowResponseDto> getMoviesInfo(Long theatreId, Long movieId) {

        List<Show> show = showRepo.findByMoviesIdAndTheatreId(movieId , theatreId);

        return showMapper.toDtoList(show);
    }
    public void generateSeatsForExistingShows() {
        List<Show> allShows = showRepo.findAll();

        for (Show show : allShows) {

            List<ShowSeat> existing = showSeatRepo.findByShow_Id(show.getId());

            if (existing.isEmpty()) {
                List<Seat> physicalSeats = seatRepo.findByScreen_Id(show.getScreens().getId());
                List<ShowSeat> newShowSeats = new ArrayList<>();
                for (Seat physicalSeat : physicalSeats) {
                    ShowSeat showSeat = new ShowSeat();
                    showSeat.setShow(show);
                    showSeat.setSeat(physicalSeat);
                    showSeat.setReserved(false);
                    newShowSeats.add(showSeat);
                }
                showSeatRepo.saveAll(newShowSeats);
                log.info("Successfully generated {} seats for Show ID: {}", newShowSeats.size(), show.getId());
            } else {
                log.info("Show ID: {} already has seats. Skipping.", show.getId());
            }
        }
    }

    public long countGeneratedSeats() {
        return showSeatRepo.count();
    }
}
