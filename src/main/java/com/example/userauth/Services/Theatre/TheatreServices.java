package com.example.userauth.Services.Theatre;

import com.example.userauth.DTOs.MovieDtos.MovieResponseDto;
import com.example.userauth.DTOs.SeatDtos.SeatResponseDto;
import com.example.userauth.DTOs.ShowDtos.ShowResponseDto;
import com.example.userauth.DTOs.ShowSeat.ShowSeatResponseDto;
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
    private final ShowMapper showMapper;
    private final seatRepo seatRepo;
    private final showSeatRepo showSeatRepo;
    private final screensRepo screensRepo;


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

    public void fixExistingShows() {
        List<Show> allShows = showRepo.findAll();
        log.info("Starting fix for " + allShows.size() + " shows.");

        for (Show show : allShows) {
            // Find physical seats for the screen
            List<Seat> physicalSeats = seatRepo.findByScreen_Id(show.getScreens().getId());

            if (physicalSeats.isEmpty()) {
                log.warn("SKIPPING Show " + show.getId() + " because Screen " + show.getScreens().getId() + " has NO physical seats!");
                continue;
            }

            // Check if show_seats already exist
            if (showSeatRepo.findByShow_Id(show.getId()).isEmpty()) {
                log.info("Working on Show " + show.getId() + ". Found " + physicalSeats.size() + " physical seats.");

                for (Seat s : physicalSeats) {
                    ShowSeat ss = new ShowSeat();
                    ss.setShow(show);
                    ss.setSeat(s);
                    ss.setReserved(false);
                    showSeatRepo.save(ss);
                }

                // This is the most important line!
                showSeatRepo.flush();
                log.info("REAL SUCCESS: Inserted seats for Show ID " + show.getId());
            } else {
                log.info("Show " + show.getId() + " already has seats. Skipping.");
            }
        }
    }

    public void generatePhysicalSeatsForScreen(Long screenId) {
        Screens screen = screensRepo.findById(screenId).orElseThrow();

        for (int row = 1; row <= screen.getTotalRows(); row++) {
            for (int col = 1; col <= screen.getSeatsPerRow(); col++) {
                Seat seat = new Seat();
                seat.setRowNo(row);
                seat.setSeatNo(col);
                seat.setScreen(screen);
                seatRepo.save(seat);
            }
        }
    }

    public List<ShowSeatResponseDto> getAllSeats(Long theatreId, Long movieId, Long showId) {
        List<ShowSeat> showSeats = showSeatRepo.findByShow_Id(showId);

        return showSeats.stream()
                .filter(ss -> ss.getSeat() != null)
                .map(showSeat -> {
                    ShowSeatResponseDto dto = new ShowSeatResponseDto();
                    dto.setId(showSeat.getId());
                    dto.setSeatNumber(showSeat.getSeat().getSeatNo());
                    dto.setRowNumber(showSeat.getSeat().getRowNo());
                    dto.setReserved(showSeat.isReserved());

                    double price = (showSeat.getShow() != null) ? showSeat.getShow().getPrice() : 0.0;
                    dto.setPrice(price);

                    return dto;
                }).toList();
    }
}
