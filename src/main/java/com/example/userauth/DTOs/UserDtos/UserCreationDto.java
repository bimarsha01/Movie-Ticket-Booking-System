package com.example.userauth.DTOs.UserDtos;


import com.example.userauth.ExceptionHandling.fieldErrorConstant;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCreationDto {

    @NotBlank(message = fieldErrorConstant.NOT_BLANK)
    @NotNull(message = fieldErrorConstant.NOT_NULL)
    private String username;

    @NotBlank(message = fieldErrorConstant.NOT_BLANK)
    @Size(min = 8 , max = 20 , message = "Minimum of eight characters and maximum of 20 characters")
    private String password;

    @NotBlank(message = fieldErrorConstant.NOT_BLANK)
    @Email(message = "Enter correct format")
    private String email;

    @NotNull(message = fieldErrorConstant.NOT_NULL)
    @Pattern(regexp="\\d{10}", message="Phone number must be 10 digits")
    private String contact;
}
