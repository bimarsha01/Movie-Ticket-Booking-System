package com.example.userauth.Repo;

import com.example.userauth.Models.AdminRequestForTheatre;
import com.example.userauth.Models.Enums.EStatus;
import com.example.userauth.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminRequestRepo extends JpaRepository<AdminRequestForTheatre , Long> {
    boolean existsByUserAndStatus(User user, EStatus status);

   List<AdminRequestForTheatre> findByStatus(EStatus status);
}
