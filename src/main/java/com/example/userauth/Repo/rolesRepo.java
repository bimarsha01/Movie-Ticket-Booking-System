package com.example.userauth.Repo;

import com.example.userauth.Models.Enums.ERole;
import com.example.userauth.Models.Movies;
import com.example.userauth.Models.Roles;
import com.example.userauth.Models.User;
import lombok.Getter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;


@Repository
public interface rolesRepo  extends JpaRepository<Roles, Long> {

    Optional<Roles> findByrole(ERole rollName);

}
