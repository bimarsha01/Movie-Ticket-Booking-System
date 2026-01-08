package com.example.userauth.Models;

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
@Table(name = "users" , uniqueConstraints = {
@UniqueConstraint(columnNames = "user_email" , name = "email" ),
        @UniqueConstraint(columnNames = "user_name" , name = "username" ),
        @UniqueConstraint(columnNames = "user_contact" , name = "contact" ),
})
@Entity
public class User extends BaseCreatedAndUpdatedAt{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "user_name" , length = 30 ,nullable = false  )
    private String username;

    @Column(name = "user_email" , length = 100 ,nullable = false )
    private String email;

    @Column(name = "user_password", nullable = false )
    private String password;

    @Column(name = "user_contact" , length = 10 ,nullable = false )
    private String contact;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles" ,
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Roles> roles = new HashSet<>();


}
