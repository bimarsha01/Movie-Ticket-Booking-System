package com.example.userauth.Repo;

import com.example.userauth.Models.Enums.ERole;
import com.example.userauth.Models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RolesRepo extends JpaRepository<Roles, Long> {

    Optional<Roles> findByrole(ERole rollName);

}
