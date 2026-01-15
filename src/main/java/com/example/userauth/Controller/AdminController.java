package com.example.userauth.Controller;

import com.example.userauth.API.ApiResponse;
import com.example.userauth.DTOs.UserDtos.UserResponseDto;
import com.example.userauth.Services.User.AdminServices;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/admin")
@RestController
@AllArgsConstructor
@Slf4j
public class AdminController extends BaseController{

    private AdminServices adminServices;

    @GetMapping("/dashboard")
    @PreAuthorize("hasAuthority('Role_Admin')")
    public String dashboard(){
       return "Welcome to admin dashboard";
    }


    @GetMapping("/getallusers")
    @PreAuthorize("hasAuthority('Role_Admin')")
    public ResponseEntity<ApiResponse> getallusers(){
        List<UserResponseDto> userResponseDto = adminServices.getAllUsers();
        if(userResponseDto == null){
            log.error("There was an error during getting all the users");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(failureResponse("Failed to Load the users", false, null));
        } else {
            log.info("Users Loaded successfully");
            return ResponseEntity.ok(successResponse("Users Loaded successfully", true, userResponseDto));
        }
        }

        @GetMapping("/getuserbyusername/{username}")
    @PreAuthorize("hasAuthority('Role_Admin')")
    public ResponseEntity<ApiResponse> getuserbyusername(@PathVariable String username){
        log.info("getting user by username");
        UserResponseDto userResponseDto = adminServices.getuserbyusername(username);
            if(userResponseDto == null){
                log.error("There was an error during getting all the users");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(failureResponse("Failed to Load the users", false, null));
            } else {
                log.info("Users Loaded successfully");
                return ResponseEntity.ok(successResponse("Users Loaded successfully", true, userResponseDto));
            }
        }

        @PostMapping("/changerole/toadmin/{username}")
        @PreAuthorize("hasAuthority('Role_Admin')")
        public ResponseEntity<ApiResponse> changeroles(@PathVariable String username){
        log.info("changing the user role to admin");
        UserResponseDto userResponseDto = adminServices.changeRoleToAdmin(username);
            if(userResponseDto == null){
                log.error("There was an error during getting all the users");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(failureResponse("Failed to change the role to admin", false, null));
            } else {
                log.info("Users Loaded successfully");
                return ResponseEntity.ok(successResponse("User " + username + " changed to admin successfully", true, userResponseDto));
            }
        }
    }

