package com.example.userauth.Controller;


import com.example.userauth.API.ApiResponse;
import com.example.userauth.DTOs.UserDtos.UserCreationDto;
import com.example.userauth.DTOs.UserDtos.UserResponseDto;
import com.example.userauth.Services.User.userService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
@RestController
@RequestMapping("/customer")
public class UserController extends BaseController {

    public final userService userService;


    @PostMapping("/signup/user")
    public ResponseEntity<ApiResponse> userSignup(@Valid @RequestBody UserCreationDto userCreationDto){
        log.info("signing up the user");
        UserResponseDto userResponseDto = userService.save(userCreationDto);

        if ((userResponseDto == null)){
            log.error("There was an error during the user creation");
            return ResponseEntity.ok(failureResponse("failed to create user", Boolean.FALSE , null));
        }
        else{
            log.info("user created successfully");
        return ResponseEntity.ok(successResponse("Created the user successfully" , Boolean.TRUE , userResponseDto));
        }
    }

    @GetMapping("/justget")
    public ResponseEntity<ApiResponse> justchecking(@Valid @RequestBody UserCreationDto userCreationDto){
        log.info("signing up the user");
        int userResponseDto = userService.getthething(userCreationDto);

            return ResponseEntity.ok(successResponse("Created the user successfully" , Boolean.TRUE , userResponseDto));
        }
}
