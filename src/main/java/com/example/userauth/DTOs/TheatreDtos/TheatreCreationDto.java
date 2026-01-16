package com.example.userauth.DTOs.TheatreDtos;


import com.example.userauth.ExceptionHandling.fieldErrorConstant;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TheatreCreationDto {


    @NotBlank(message = fieldErrorConstant.NOT_BLANK)
    @NotNull(message = fieldErrorConstant.NOT_NULL)
    private String name;

    @NotNull(message = fieldErrorConstant.NOT_NULL)
    private String contact;

    @NotBlank(message = fieldErrorConstant.NOT_BLANK)
    @NotNull(message = fieldErrorConstant.NOT_NULL)
    private String location;


}
