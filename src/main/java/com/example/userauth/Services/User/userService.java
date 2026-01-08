package com.example.userauth.Services.User;

import com.example.userauth.DTOs.UserDtos.UserCreationDto;
import com.example.userauth.DTOs.UserDtos.UserResponseDto;
import com.example.userauth.Mapper.userMapper;
import com.example.userauth.Models.Enums.ERole;
import com.example.userauth.Models.Roles;
import com.example.userauth.Models.User;
import com.example.userauth.Repo.UserRepo;
import com.example.userauth.Repo.rolesRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Transactional
@Service
@Slf4j
@AllArgsConstructor
@Getter
@Setter
public class userService {

    private final userMapper userMapper;
    private final UserRepo userRepo;
    private final rolesRepo rolesRepo;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDto save(UserCreationDto userCreationDto){
        log.info("user is being created");
        User user = userMapper.toEntity(userCreationDto);

        String encodedPw = passwordEncoder.encode(userCreationDto.getPassword());
        user.setPassword(encodedPw);

        Roles defaultroles = rolesRepo.findByrole(ERole.Role_User)
                .orElseThrow(() -> new RuntimeException("Error: Role_User not found in DB"));

        user.setRoles(Set.of(defaultroles));
        
        User savedUser = userRepo.save(user);
        
        log.info("user saved with id: {}", savedUser.getUserId());
        return userMapper.toDto(savedUser);
    }
}
