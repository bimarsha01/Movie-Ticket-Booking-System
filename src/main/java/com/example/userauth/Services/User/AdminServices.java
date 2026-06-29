package com.example.userauth.Services.User;

import com.example.userauth.DTOs.AdminRequestDto;
import com.example.userauth.DTOs.UserDtos.UserResponseDto;
import com.example.userauth.ExceptionHandling.NotAvailableException;
import com.example.userauth.ExceptionHandling.NotFoundException;
import com.example.userauth.Mapper.AdminRequestMapper;
import com.example.userauth.Mapper.userMapper;
import com.example.userauth.Models.AdminRequestForTheatre;
import com.example.userauth.Models.Enums.ERole;
import com.example.userauth.Models.Enums.EStatus;
import com.example.userauth.Models.Roles;
import com.example.userauth.Models.User;
import com.example.userauth.Repo.AdminRequestRepo;
import com.example.userauth.Repo.UserRepo;
import com.example.userauth.Repo.RolesRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Transactional
@Service
@Slf4j
@RequiredArgsConstructor

public class AdminServices {

    private final UserRepo userRepo;
    private final userMapper userMapper;
    private final RolesRepo rolesRepo;
    private final AdminRequestRepo adminRequestRepo;
    private final AdminRequestMapper adminRequestMapper;

    public List<UserResponseDto> getAllUsers() {
        log.info("finding all the users");
        List<User> userList = userRepo.findAll();
        return userList.stream().map(user -> userMapper.toDto(user)).toList();
    }

    public UserResponseDto getUserByUsername(String username) {

        log.info("getting the user with the username");

        User user = userRepo.findByUsername(username).
                orElseThrow(()-> new NotFoundException("NOT_FOUND" , "USERNAME " + username + " not  found try the valid one"));

        return userMapper.toDto(user);
    }

    public UserResponseDto changeRoleToAdmin(String username) {
        log.info("changing the user role to admin in process: ");
        User user = userRepo.findByUsername(username)
                .orElseThrow(()->  new NotFoundException("NOT_FOUND" , "USERNAME " + username + " not  found try the valid one"));

        Roles forAdmin = rolesRepo.findByrole(ERole.Role_Admin).orElseThrow(()-> new NotAvailableException("Not available " , "THIS IS NOT AVAILABLE FOR OPERATION"));

        Set<Roles> newRoles = new HashSet<>();
        newRoles.add(forAdmin);

        user.setRoles(newRoles);

       User updated =  userRepo.save(user);

        return userMapper.toDto(updated);
    }

    public List<AdminRequestDto> pendingTheatreRequest() {
        log.info("getting all the pending theatre request done by the admin");

        List<AdminRequestForTheatre> adminRequestForTheatre = adminRequestRepo.findByStatus(EStatus.Pending);

       return adminRequestMapper.toDtoList(adminRequestForTheatre);
    }



    public AdminRequestDto approveRequest(Long Id) {
        log.info("aproove the request sent by the user admin");

        AdminRequestForTheatre adminRequestForTheatre = adminRequestRepo.findById(Id).orElseThrow(()->new NotFoundException("NOT_FOUND" , "THE ID" + Id + " is not in the DB"));

        adminRequestForTheatre.setStatus(EStatus.Successful);

        Roles theatreRole = rolesRepo.findByrole(ERole.Role_Theatre_Admin).orElseThrow(()->new NotFoundException("NOT_FOUND" , "THIS ROLE IS NOT IN THE DB"));

        Set<Roles> newRoles = new HashSet<>();
        newRoles.add(theatreRole);

        User user = adminRequestForTheatre.getUser();
        log.info("setting up the new roles");
        user.setRoles(newRoles);
        userRepo.save(user);
        log.info("this is the role " + user.getRoles());

        adminRequestRepo.save(adminRequestForTheatre);
        return adminRequestMapper.toDto(adminRequestForTheatre);
    }
}
