package com.example.userauth.Config;

import com.example.userauth.ExceptionHandling.NotFoundException;
import com.example.userauth.Models.Enums.ERole;
import com.example.userauth.Models.Roles;
import com.example.userauth.Models.User;
import com.example.userauth.Repo.UserRepo;
import com.example.userauth.Repo.RolesRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
@Component
@AllArgsConstructor
public class FirstRun implements CommandLineRunner {
    private final RolesRepo rolesRepo;
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        for(ERole Erole : ERole.values()){
            if(rolesRepo.findByrole(Erole).isEmpty()){
                Roles roles = new Roles();
                roles.setRole(Erole);
                rolesRepo.save(roles);
            }
            log.info("if new roles has been assigned or is being run the first time");
        }
        if(userRepo.findByUsername("admin").isEmpty()){
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("adminbimarsha@gmail.com");
            admin.setPassword(passwordEncoder.encode("admin@123"));
            admin.setContact("9813425643");

            log.info("creating a admin if it does not exist");

            Roles adminRole = rolesRepo.findByrole(ERole.Role_Admin)
                    .orElseThrow(()->new NotFoundException("NOT,FOUND" , "ADMIN ROLE NOT FOUND"));

            admin.setRoles(Set.of(adminRole));
            userRepo.save(admin);
            log.info("Admin created with the username : " + admin.getUsername() );
        }
    }
}
