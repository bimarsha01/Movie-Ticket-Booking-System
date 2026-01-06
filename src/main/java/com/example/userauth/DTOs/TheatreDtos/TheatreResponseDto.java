package com.example.userauth.DTOs.TheatreDtos;


import com.example.userauth.Models.Screens;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TheatreResponseDto {

    private Long id;
    private String name;
    private String contact;
    private String location;
    private Set<Screens> screensSet;
}
