package com.example.userauth.Controller;


import com.example.userauth.API.ApiResponse;
import com.example.userauth.DTOs.ChangepasswordDto;
import com.example.userauth.DTOs.UserDtos.SigninDto;
import com.example.userauth.DTOs.UserDtos.UserCreationDto;
import com.example.userauth.DTOs.UserDtos.UserResponseDto;
import com.example.userauth.Services.User.userService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.example.userauth.DTOs.Others.jwtResponse;

@Slf4j
@RestController
@RequestMapping("/customer")
@AllArgsConstructor
public class UserController extends BaseController {

    private final userService userServices;

    @PostMapping("/signup/user")
    public ResponseEntity<ApiResponse> userSignup(@Valid @RequestBody UserCreationDto userCreationDto) {
        log.info("Signing up the user");

        UserResponseDto userResponseDto = userServices.save(userCreationDto);

        if (userResponseDto == null) {
            log.error("There was an error during user creation");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(failureResponse("Failed to create user", false, null));
        } else {
            log.info("User created successfully");
            return ResponseEntity.ok(successResponse("Created the user successfully", true, userResponseDto));
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<ApiResponse> adminSignin(@Valid @RequestBody SigninDto signinDto){
        log.info("Signing in the user");

        jwtResponse userResponseDto = userServices.signin(signinDto);
        if (userResponseDto == null) {
            log.error("There was an error during user creation");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(failureResponse("Failed to create user", false, null));
        } else {
            log.info("User created successfully");
            return ResponseEntity.ok(successResponse("Created the user successfully", true, userResponseDto));
        }
    }

    @PostMapping("/change-password")
    @PreAuthorize("hasAnyAuthority('Role_User' , 'Role_Admin' , 'Role_Theatre_Admin')")
    public ResponseEntity<ApiResponse> changePassword(@RequestBody ChangepasswordDto cp){

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

         userServices.changePassword(username , cp);

        return ResponseEntity.ok(successResponse("Password updated successfully", true, null));
    }


    @PostMapping("/request/theatreAdmin")
    @PreAuthorize("hasAuthority('Role_User')")
    public ResponseEntity<ApiResponse> becomeTheatreAdmin() {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        userServices.applyForTheatreAdmin(username);

        return ResponseEntity.ok(successResponse("PENDING YOUR REQUEST", true, null));
    }
}

