package com.example.userauth.Controller;

import com.example.userauth.API.ApiResponse;
import com.example.userauth.DTOs.ShowDtos.ShowResponseDto;
import com.example.userauth.DTOs.ShowSeat.ShowSeatResponseDto;
import com.example.userauth.DTOs.TheatreDtos.TheatreResponseDto;
import com.example.userauth.Helpers.MovieMinInfo;
import com.example.userauth.Repo.ShowSeatRepo;
import com.example.userauth.Services.Theatre.TheatreServices;
import com.example.userauth.Services.User.AdminServices;
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
    private final ShowSeatRepo showSeatRepo;
    private final AdminServices adminServices;

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

    @GetMapping("/show-all-theatres/{theatreId}/{movieId}/{showId}/seats")
    @PreAuthorize("hasAuthority('Role_User')")
    public ResponseEntity<ApiResponse> getShowDetailsAndSeatDetails(@PathVariable Long theatreId , @PathVariable Long movieId , @PathVariable Long showId){
        List<ShowSeatResponseDto> showSeatResponseDto = theatreServices.getAllSeats(theatreId , movieId , showId);
        return ResponseEntity.ok(successResponse("Seats for show with id :" + showId , true , showSeatResponseDto));
    }

}
