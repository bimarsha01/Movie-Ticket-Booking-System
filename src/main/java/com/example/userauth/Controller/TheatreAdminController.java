package com.example.userauth.Controller;

import com.example.userauth.API.ApiResponse;
import com.example.userauth.DTOs.TheatreDtos.TheatreCreationDto;
import com.example.userauth.DTOs.TheatreDtos.TheatreResponseDto;
import com.example.userauth.Services.User.TheatreAdminServices;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/TheatreAdmin")
@AllArgsConstructor
public class TheatreAdminController extends  BaseController{

    private final TheatreAdminServices theatreAdminServices;

    @PostMapping("/createTheatre")
    @PreAuthorize("hasAuthority('Role_Theatre_Admin')")

    public ResponseEntity<ApiResponse> createTheatre(@RequestBody TheatreCreationDto theatreCreationDto){
        TheatreResponseDto theatreResponseDto = theatreAdminServices.createTheatres(theatreCreationDto);
        if(theatreResponseDto == null){
            return ResponseEntity.ok(successResponse("Error creating the theatre" , false , null));

        }
        return ResponseEntity.ok((successResponse("Theatre creation successful" , true , theatreResponseDto)));
    }
    @GetMapping("/showAllTheatre")
    @PreAuthorize("hasAuthority('Role_Theatre_Admin')")

    public ResponseEntity<ApiResponse> showAllTheatre(){
        List<TheatreResponseDto> theatreResponseDto = theatreAdminServices.getAllTheatres();
        if(theatreResponseDto == null){
            return ResponseEntity.ok(successResponse("Error creating the theatre" , false , null));

        }
        return ResponseEntity.ok((successResponse("Loading Theatre successful" , true , theatreResponseDto)));
    }


}
