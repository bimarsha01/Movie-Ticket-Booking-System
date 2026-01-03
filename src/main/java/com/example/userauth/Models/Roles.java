package com.example.userauth.Models;


import com.example.userauth.Models.Enums.ERole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

  @Enumerated(EnumType.STRING)
  @Column(name = "role_name" , length = 20)
    public ERole role;

    @ManyToMany(mappedBy = "roles" )
    private Set<User> users = new HashSet<>();
}
