package com.example.userauth.Services.Theatre;

import com.example.userauth.DTOs.TheatreDtos.TheatreCreationDto;
import com.example.userauth.DTOs.TheatreDtos.TheatreResponseDto;
import com.example.userauth.ExceptionHandling.NotFoundException;
import com.example.userauth.Mapper.TheatreMapper;
import com.example.userauth.Models.Theatre;
import com.example.userauth.Models.User;
import com.example.userauth.Repo.UserRepo;
import com.example.userauth.Repo.TheatreRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
@Slf4j
@RequiredArgsConstructor
public class TheatreAdminServices {

    private final UserRepo userRepo;
    private final TheatreMapper theatreMapper;
    private final TheatreRepo theatreRepo;

    public TheatreResponseDto createTheatres(TheatreCreationDto theatreCreationDto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        log.info("Creating the theatre of " + username);
        User user = userRepo.findByUsername(username)
                .orElseThrow(()-> new NotFoundException("NOT_FOUND" , "User with username " + username + " not found"));
        Theatre theatre = theatreMapper.toEntity(theatreCreationDto);

        theatre.setUser(user);
        Theatre save = theatreRepo.save(theatre);
        log.info("Theatre creation successful");
        return theatreMapper.toDto(save);

    }

    public List<TheatreResponseDto> getAllTheatres() {
        log.info("getting all the theatres");
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Theatre> theatresByUserUsername = theatreRepo.getTheatresByUser_Username(username);
        return theatreMapper.toDtoList(theatresByUserUsername);
    }
}
