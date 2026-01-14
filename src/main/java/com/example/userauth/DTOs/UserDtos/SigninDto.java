package com.example.userauth.DTOs.UserDtos;

import com.example.userauth.ExceptionHandling.fieldErrorConstant;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SigninDto {

    private String username;

    @Size(message = "Minimum of eight characters and maximum of 20 characters")
    private String password;
}
