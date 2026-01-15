package com.example.userauth.DTOs;

import com.example.userauth.Models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminRequestDto {
    private User user;
    private Set<String> status;


}
