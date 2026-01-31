package com.example.userauth.Config;

import com.example.userauth.Models.Enums.ERole;
import com.example.userauth.Models.Roles;
import com.example.userauth.Repo.RolesRepo;
import com.example.userauth.Services.User.userService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleDataLoader implements CommandLineRunner {

    private final RolesRepo rolesRepo;
    private final userService userService;

    @Override
    public void run(String... args) throws Exception {
        if( rolesRepo.findByrole(ERole.Role_User).isEmpty()){
            Roles userRole = new Roles();
            userRole.setRole(ERole.Role_User);
            rolesRepo.save(userRole);
        }
        if( rolesRepo.findByrole(ERole.Role_Admin).isEmpty()){
            Roles userRole = new Roles();
            userRole.setRole(ERole.Role_Admin);
            rolesRepo.save(userRole);
        }
        if( rolesRepo.findByrole(ERole.Role_Moderator).isEmpty()){
            Roles userRole = new Roles();
            userRole.setRole(ERole.Role_Moderator);
            rolesRepo.save(userRole);
        }if( rolesRepo.findByrole(ERole.Role_Theatre_Admin).isEmpty()){
            Roles userRole = new Roles();
            userRole.setRole(ERole.Role_Theatre_Admin);
            rolesRepo.save(userRole);
        }
    }
}
