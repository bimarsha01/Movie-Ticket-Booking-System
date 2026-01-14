package com.example.userauth.Controller;

import com.example.userauth.API.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/admin")
@RestController
@AllArgsConstructor
public class AdminController extends BaseController{

    @GetMapping("/dashboard")
    @PreAuthorize("hasAuthority('Role_Admin')")
    public String dashboard(){
       return "Welcome to admin dashboard";
    }

}
