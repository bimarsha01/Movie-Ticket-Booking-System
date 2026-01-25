package com.example.userauth.Controller;


import com.example.userauth.API.ApiResponse;
import com.example.userauth.DTOs.MovieDtos.MovieCreationDto;
import com.example.userauth.DTOs.MovieDtos.MovieResponseDto;
import com.example.userauth.Services.Movies.MovieServices;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/movie")
@AllArgsConstructor
public class MovieController extends BaseController{

    private final MovieServices movieServices;

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('Role_Admin')")
    public ResponseEntity<ApiResponse> addMovies(@RequestBody List<MovieCreationDto> dto){
        log.info("Adding the movie in the db");
        List<MovieResponseDto> movieResponseDto = movieServices.addMovies(dto);
        if(movieResponseDto == null){
            return ResponseEntity.ok(failureResponse("Adding mocies into db failed " , false , null));

        }
        return ResponseEntity.ok(successResponse("Movie added successfully" , true , movieResponseDto));
    }
}
