package com.example.userauth.Repo;

import com.example.userauth.Models.Movies;
import com.example.userauth.Models.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface theatreRepo  extends JpaRepository<Theatre, Long> {
    List<Theatre> getTheatresByUser_Username(String userUsername);

    Theatre findByUser_Username(String userUsername);
}
