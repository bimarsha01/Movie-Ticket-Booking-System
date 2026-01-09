package com.example.userauth.Controller;


import com.example.userauth.API.ApiResponse;
import com.example.userauth.DTOs.UserDtos.SigninDto;
import com.example.userauth.DTOs.UserDtos.UserCreationDto;
import com.example.userauth.DTOs.UserDtos.UserResponseDto;
import com.example.userauth.Services.User.userService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/signin/admin")
    public ResponseEntity<ApiResponse> adminSignin(@Valid @RequestBody SigninDto signinDto){
        log.info("Signing in the user");

        UserResponseDto userResponseDto = userServices.signin(signinDto);
        if (userResponseDto == null) {
            log.error("There was an error during user creation");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(failureResponse("Failed to create user", false, null));
        } else {
            log.info("User created successfully");
            return ResponseEntity.ok(successResponse("Created the user successfully", true, userResponseDto));
        }
    }
}
