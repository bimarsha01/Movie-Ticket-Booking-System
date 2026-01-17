package com.example.userauth.Controller;


import com.example.userauth.API.ApiResponse;
import com.example.userauth.DTOs.ScreenDtos.ScreenCreationDto;
import com.example.userauth.DTOs.ScreenDtos.ScreenResponseDto;
import com.example.userauth.Services.Screen.ScreenServices;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/screen")
@AllArgsConstructor
public class ScreenController extends BaseController {

    private final ScreenServices screenServices;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('Role_Theatre_Admin')")
    public ResponseEntity<ApiResponse> addScreen(@RequestBody ScreenCreationDto screenCreationDto){
        ScreenResponseDto screenResponseDto = screenServices.addScreen(screenCreationDto);

        if(screenResponseDto == null){
            return ResponseEntity.ok(failureResponse("THE OPERATION DID NOT CARRIED ON " , false , null));

        }
        return ResponseEntity.ok(successResponse("SCREEN CREATED SUCCESSFULLY " , true , screenResponseDto));
    }
}
