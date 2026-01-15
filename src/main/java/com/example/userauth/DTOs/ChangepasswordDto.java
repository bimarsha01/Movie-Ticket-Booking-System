package com.example.userauth.DTOs;

import lombok.Getter;

@Getter
public class ChangepasswordDto {
    private String oldPassword;
    private String newPassword;
}
