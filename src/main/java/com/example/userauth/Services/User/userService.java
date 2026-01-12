package com.example.userauth.Services.User;

import com.example.userauth.Config.JwtService;
import com.example.userauth.DTOs.UserDtos.SigninDto;
import com.example.userauth.DTOs.UserDtos.UserCreationDto;
import com.example.userauth.DTOs.UserDtos.UserResponseDto;
import com.example.userauth.ExceptionHandling.InvalidCredentialsException;
import com.example.userauth.ExceptionHandling.NotFoundException;
import com.example.userauth.Mapper.userMapper;
import com.example.userauth.Models.Enums.ERole;
import com.example.userauth.Models.Roles;
import com.example.userauth.Models.User;
import com.example.userauth.Repo.UserRepo;
import com.example.userauth.Repo.rolesRepo;
import io.jsonwebtoken.Jwt;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

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
//    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final AuthenticationManagerBuilder authenticate;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtServices;
    private final UserDetailsService userDetailsService;

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

    public UserResponseDto signin(SigninDto signinDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinDto.getUsername() , signinDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtServices.generateJwtToken(authentication);

//         userdetailsimpl =(userDetailsService) authentication.getPrincipal();
        return null;
    }


}
