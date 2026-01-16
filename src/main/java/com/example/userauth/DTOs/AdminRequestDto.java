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
    private Long id;
    private Long userId;
    private String username;
    private String status;
    private String adminComment;
}
