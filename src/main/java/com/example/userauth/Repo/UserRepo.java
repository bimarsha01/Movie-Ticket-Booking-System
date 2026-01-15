package com.example.userauth.Repo;

import com.example.userauth.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepo extends JpaRepository<User , Long> {

    Optional<User> findByUsername(String name);
}
