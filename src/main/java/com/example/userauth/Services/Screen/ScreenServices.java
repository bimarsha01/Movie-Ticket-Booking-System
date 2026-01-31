package com.example.userauth.Services.Screen;

import com.example.userauth.DTOs.ScreenDtos.ScreenCreationDto;
import com.example.userauth.DTOs.ScreenDtos.ScreenResponseDto;
import com.example.userauth.ExceptionHandling.NotFoundException;
import com.example.userauth.Mapper.ScreenMapper;
import com.example.userauth.Models.Screens;
import com.example.userauth.Models.Theatre;
import com.example.userauth.Repo.UserRepo;
import com.example.userauth.Repo.ScreensRepo;
import com.example.userauth.Repo.TheatreRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.example.userauth.Services.Theatre.TheatreServices;

import java.util.List;

@Transactional
@Service
@Slf4j
@RequiredArgsConstructor
public class ScreenServices {
    private final ScreensRepo screensRepo;
    private final UserRepo userRepo;
    private final TheatreRepo theatreRepo;
    private final ScreenMapper screenMapper;
    private final TheatreServices theatreServices;

    public ScreenResponseDto addScreen(ScreenCreationDto screenCreationDto) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Theatre theatre = theatreRepo.findById(screenCreationDto.getTheatreId()).
                orElseThrow(() -> new NotFoundException("NOT_FOUND ", "The theatre you are inserted does not exist"));

        if (!theatre.getUser().getUsername().equals(username)) {
            throw new BadCredentialsException("You cannot access this theatre");

        }

        Screens entity = screenMapper.toEntity(screenCreationDto);
        entity.setTheatre(theatre);
        entity.setTotalSeats(screenCreationDto.getSeatsPerRow() * screenCreationDto.getTotalRows());

        screensRepo.save(entity);
        theatreServices.generatePhysicalSeatsForScreen(entity.getId());
        return screenMapper.toDto(entity);

    }


    public List<ScreenResponseDto> getAllScreens() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Theatre theatre = theatreRepo.findByUser_Username(username);

        if (theatre == null) {
            throw new NotFoundException("THEATRE_NOT_FOUND", "Theatre not found for user: " + username);
        }

        return theatre.getScreens().stream()
                .map(screen -> new ScreenResponseDto(
                        screen.getId(),
                        screen.getScreenNo(),
                        theatre.getId(),
                        screen.getTotalRows(),
                        screen.getSeatsPerRow(),
                        screen.getTotalSeats()
                )).toList();
    }
}
