package com.example.userauth.DTOs.Others;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class jwtResponse {
   private String token;
   private String type = "Bearer";
   private long id;
   private String username;
   private List<String> roles;

}
