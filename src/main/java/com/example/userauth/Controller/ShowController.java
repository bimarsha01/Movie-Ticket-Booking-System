package com.example.userauth.Controller;

import com.example.userauth.API.ApiResponse;
import com.example.userauth.DTOs.ShowDtos.ShowCreationDto;
import com.example.userauth.DTOs.ShowDtos.ShowResponseDto;
import com.example.userauth.Services.Show.ShowServices;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/show")
@AllArgsConstructor
public class ShowController extends BaseController{
    private final ShowServices showServices;

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('Role_Theatre_Admin')")
    public ResponseEntity<ApiResponse> addShows(@RequestBody List<ShowCreationDto> showCreationDto){
        List<ShowResponseDto> showResponseDto = showServices.addShows(showCreationDto);

    return ResponseEntity.ok(successResponse("Show has been created " , true , showResponseDto));
    }

    @GetMapping("/getShows")
    @PreAuthorize("hasAnyAuthority('Role_User' , 'Role_Theatre_Admin' , 'Role_Admin')")
    public ResponseEntity<ApiResponse> getAllShows(){
        List<ShowResponseDto> showResponseDto = showServices.getAllShows();

        return ResponseEntity.ok(successResponse("FETCHING ALL THE SHOWS SUCCESSFUL " , true , showResponseDto));
    }
}
