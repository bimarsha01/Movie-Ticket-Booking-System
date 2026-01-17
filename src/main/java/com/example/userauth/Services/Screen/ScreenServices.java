package com.example.userauth.Services.Screen;

import com.example.userauth.DTOs.ScreenDtos.ScreenCreationDto;
import com.example.userauth.DTOs.ScreenDtos.ScreenResponseDto;
import com.example.userauth.ExceptionHandling.NotFoundException;
import com.example.userauth.Mapper.ScreenMapper;
import com.example.userauth.Models.Screens;
import com.example.userauth.Models.Theatre;
import com.example.userauth.Models.User;
import com.example.userauth.Repo.UserRepo;
import com.example.userauth.Repo.screensRepo;
import com.example.userauth.Repo.theatreRepo;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Transactional
@Service
@Slf4j
@RequiredArgsConstructor
public class ScreenServices {
    private final screensRepo screensRepo;
    private final UserRepo userRepo;
    private final theatreRepo theatreRepo;
    private final ScreenMapper screenMapper;

    public ScreenResponseDto addScreen(ScreenCreationDto screenCreationDto) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

       Theatre theatre = theatreRepo.findById(screenCreationDto.getTheatreId()).
               orElseThrow(()-> new NotFoundException("NOT_FOUND " , "The theatre you are inserted does not exist"));

       if(!theatre.getUser().getUsername().equals(username)){
           throw new BadCredentialsException("You cannot access this theatre");

           }

       Screens entity = screenMapper.toEntity(screenCreationDto);
       entity.setTheatre(theatre);
       entity.setTotalSeats(screenCreationDto.getSeatsPerRow()*screenCreationDto.getTotalRows());

       screensRepo.save(entity);

       return screenMapper.toDto(entity);

    }
}
