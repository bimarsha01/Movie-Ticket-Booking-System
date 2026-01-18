package com.example.userauth.Controller;

import com.example.userauth.API.ApiResponse;
import com.example.userauth.DTOs.MovieDtos.MovieResponseDto;
import com.example.userauth.DTOs.ShowDtos.ShowResponseDto;
import com.example.userauth.DTOs.TheatreDtos.TheatreResponseDto;
import com.example.userauth.Helpers.MovieMinInfo;
import com.example.userauth.Services.Theatre.TheatreServices;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/theatre")
@AllArgsConstructor
public class TheatreController extends BaseController{

    private final TheatreServices theatreServices;

    @GetMapping("/show-all-theatres")
    @PreAuthorize("hasAnyAuthority('Role_User' , 'Role_Admin' , 'Role_Theatre_Admin')")

    public ResponseEntity<ApiResponse> showAllTheatre(){
        List<TheatreResponseDto> theatreResponseDto = theatreServices.showAllTheatre();

        return ResponseEntity.ok(successResponse("All Thes Theatres" , true , theatreResponseDto));
    }

    @GetMapping("/show-all-theatres/{theatreId}")
    @PreAuthorize("hasAuthority('Role_User')")
    public ResponseEntity<ApiResponse> getMoviesOfEachTheatre(@PathVariable Long theatreId){
        List<MovieMinInfo> theatreResponseDtos = theatreServices.getMoviesOfEachTheatre(theatreId);

        return ResponseEntity.ok(successResponse("Movies for the theatre " + theatreId , true , theatreResponseDtos));
    }

    @GetMapping("/show-all-theatres/{theatreId}/{movieId}")
    @PreAuthorize("hasAuthority('Role_User')")
    public ResponseEntity<ApiResponse> getMoviesInfo(@PathVariable Long theatreId , @PathVariable Long movieId){
        List<ShowResponseDto> showResponseDto = theatreServices.getMoviesInfo(theatreId , movieId);

        return ResponseEntity.ok(successResponse("Movies for the theatre " + theatreId , true , showResponseDto));
    }

    @PostMapping("/admin/backfill-seats")
    @PreAuthorize("hasAuthority('Role_Admin')")
    public ResponseEntity<String> backfill() {
        theatreServices.generateSeatsForExistingShows();
        return ResponseEntity.ok("All existing shows now have 225 seats generated!");
    }

    @GetMapping("/count")
    @PreAuthorize("hasAuthority('Role_Admin')")
    public ResponseEntity<String> something(){
      Long number =  theatreServices.countGeneratedSeats();
        return ResponseEntity.ok("this is the number " + number);
    }

}
