package com.example.userauth.Services.Show;

import com.example.userauth.DTOs.ShowDtos.ShowCreationDto;
import com.example.userauth.DTOs.ShowDtos.ShowResponseDto;
import com.example.userauth.ExceptionHandling.AlreadyExistException;
import com.example.userauth.ExceptionHandling.NotFoundException;
import com.example.userauth.ExceptionHandling.UnauthorizedException;
import com.example.userauth.Mapper.ShowMapper;
import com.example.userauth.Models.*;
import com.example.userauth.Repo.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
@Slf4j
@RequiredArgsConstructor
public class ShowServices {
    private final UserRepo userRepo;
    private final theatreRepo theatreRepo;
    private final screensRepo screensRepo;
    private final moviesRepo moviesRepo;
    private final ShowMapper showMapper;
    private final showRepo showRepo;

    public List<ShowResponseDto> addShows(List<ShowCreationDto> showCreationDto) {
        log.info("Show being checked");
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Theatre theatre = theatreRepo.findByUser_Username(username);
        log.info("Confirming if the theatre exists or not ");

        if (theatre == null) {
            throw new NotFoundException("THEATRE_NOT_FOUND", "Theatre not found with username : " + username);
        }

        List<Show> toSaveRightNow = new ArrayList<>();
        log.info("Checking the input is valid or not");

        for (ShowCreationDto dto : showCreationDto) {
            boolean isScreenOfUser = theatre.getScreens().stream()
                    .anyMatch(s -> s.getId().equals(dto.getScreenId()));

            if (!isScreenOfUser) {
                throw new UnauthorizedException("UNAUTHORIZED", "YOU ARE NOT AUTHORIZED TO CHANGE THE SHOW HERE");
            }

            Screens screens = screensRepo.findById(dto.getScreenId())
                    .orElseThrow(() -> new NotFoundException("NOT_FOUND", "SCREEN OF ID " + dto.getScreenId() + " not found "));

            Movies movie = moviesRepo.findById(dto.getMovieId())
                    .orElseThrow(() -> new NotFoundException("MOVIE_NOT_FOUND", "Movie not found"));

            log.info("Checking if the new show collides with any of the existing shows scheduled");
            LocalDateTime startTime = dto.getStartTime();
            LocalDateTime endTime = startTime.plusMinutes(movie.getDuration() + 15);

            // Check against DB shows for this specific screen
            boolean showsCollisionInDb = screens.getShows().stream().anyMatch(existingShow -> {
                return startTime.isBefore(existingShow.getEndTime()) && endTime.isAfter(existingShow.getStartTime());
            });

            // Check against shows in the current batch
            boolean collisionInCurrentList = toSaveRightNow.stream()
                    .filter(s -> s.getScreens().getId().equals(dto.getScreenId()))
                    .anyMatch(newShow -> startTime.isBefore(newShow.getEndTime()) && endTime.isAfter(newShow.getStartTime()));

            if (showsCollisionInDb || collisionInCurrentList) {
                throw new AlreadyExistException("ALREADY_EXISTS", "THERE ALREADY EXIST A SHOW THAT EXISTS AT THIS TIME");
            }

            Show show = showMapper.toEntity(dto);
            show.setMovies(movie);
            show.setTheatre(theatre);
            show.setScreens(screens);
            show.setEndTime(endTime);
            toSaveRightNow.add(show);

        }

        log.info("Saving the show ");
        List<Show> savedShows = showRepo.saveAll(toSaveRightNow);

        return savedShows.stream()
                .map(showMapper::toDto)
                .toList();
    }

    public List<ShowResponseDto> getAllShows() {

    List<Show> AllShows = showRepo.findAllByEndTimeAfter(LocalDateTime.now());
//    List<Show> AllShows = showRepo.findAll();

    return showMapper.toDtoList(AllShows);


    }
}
