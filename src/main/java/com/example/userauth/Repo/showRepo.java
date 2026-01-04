package com.example.userauth.Repo;

import com.example.userauth.Models.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface showRepo extends JpaRepository<Show , Long> {

}
