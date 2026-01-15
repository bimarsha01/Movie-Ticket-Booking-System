package com.example.userauth.Services.User;

import com.example.userauth.Config.JwtService;
import com.example.userauth.DTOs.ChangepasswordDto;
import com.example.userauth.DTOs.UserDtos.SigninDto;
import com.example.userauth.DTOs.UserDtos.UserCreationDto;
import com.example.userauth.DTOs.UserDtos.UserResponseDto;
import com.example.userauth.ExceptionHandling.InvalidCredentialsException;
import com.example.userauth.ExceptionHandling.NotAvailableException;
import com.example.userauth.ExceptionHandling.NotFoundException;
import com.example.userauth.Mapper.userMapper;
import com.example.userauth.Models.AdminRequestForTheatre;
import com.example.userauth.Models.Enums.ERole;
import com.example.userauth.Models.Enums.EStatus;
import com.example.userauth.Models.Roles;
import com.example.userauth.Models.User;
import com.example.userauth.Repo.AdminRequestRepo;
import com.example.userauth.Repo.UserRepo;
import com.example.userauth.Repo.rolesRepo;
import com.sun.jdi.request.DuplicateRequestException;
import io.jsonwebtoken.Jwt;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.juli.logging.Log;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.userauth.Helpers.userDetailsImpl;
import com.example.userauth.DTOs.Others.jwtResponse;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
@Service
@Slf4j
@RequiredArgsConstructor
@Getter
@Setter
public class userService {

    private final userMapper userMapper;
    private final UserRepo userRepo;
    private final rolesRepo rolesRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticate;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtServices;
    private final UserDetailsService userDetailsService;
    private final AdminRequestRepo adminRequestRepo;


    public UserResponseDto save(UserCreationDto userCreationDto){
        log.info("user is being created");
        User user = userMapper.toEntity(userCreationDto);

        String encodedPw = passwordEncoder.encode(userCreationDto.getPassword());
        user.setPassword(encodedPw);

        Roles defaultroles = rolesRepo.findByrole(ERole.Role_User)
                .orElseThrow(() -> new RuntimeException("Error: Role_User not found in DB"));

        user.setRoles(Set.of(defaultroles));

        String roles = user.getRoles().toString();
        System.out.println(roles);
        
        User savedUser = userRepo.save(user);
        
        log.info("user saved with id: {}", savedUser.getUserId());
        log.info("this user is being saved in the database and some fixes needs to be done");
        return userMapper.toDto(savedUser);
    }

    public jwtResponse signin(SigninDto signinDto) {
        Authentication authentication;
        try {
            log.info("S1: before authenticate");
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(signinDto.getUsername(), signinDto.getPassword())
            );
            log.info("S2: after authenticate");
        } catch (Exception e) {
            log.error("Authentication failed", e);
            throw e;
        }

        log.info("S3: before token");
        String jwt = jwtServices.generateJwtToken(authentication);
        log.info("S4: after token, jwt = {}", jwt);

        try {
            log.info("S5: before getPrincipal");
            userDetailsImpl userDetails = (userDetailsImpl) authentication.getPrincipal();
            log.info("S6: after getPrincipal");

            log.info("S7: before roles stream");
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .toList();
            log.info("S8: after roles stream, roles={}", roles);

            log.info("S9: before building jwtResponse");
            return new jwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), roles);
        } catch (Exception e) {
            log.error("S_ERR: failed building jwtResponse", e);
            throw e;
        }
    }

    public void  changePassword(String username, @Valid ChangepasswordDto cp) {

        User user = userRepo.findByUsername(username).orElseThrow(()-> new NotFoundException("NOT_FOUND" , "Username " + username+ " not found"));

        if(!passwordEncoder.matches(cp.getOldPassword() , user.getPassword())){
            throw new InvalidCredentialsException("INCORRECT CREDENTIALS" , " Old Password did not matched ");

        }

        user.setPassword(passwordEncoder.encode(cp.getNewPassword()));
        userRepo.save(user);
    }

    @Transactional
    public void applyForTheatreAdmin(String username) {
        User user = userRepo.findByUsername(username).orElseThrow(()->new NotFoundException("NOT_FOUND" , "Username not found"));

        if(adminRequestRepo.existsByUserAndStatus(user , EStatus.Pending)){
            throw new DuplicateRequestException();
        }
        AdminRequestForTheatre adminRequestForTheatre = new AdminRequestForTheatre();
        adminRequestForTheatre.setUser(user);
        adminRequestForTheatre.setStatus(EStatus.Pending);
        adminRequestRepo.save(adminRequestForTheatre);
    }
}
