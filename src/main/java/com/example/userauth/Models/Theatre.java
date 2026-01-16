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

@Entity
public class Theatre extends BaseCreatedAndUpdatedAt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name" , length = 100 , nullable = false)
    private String name;

    @Column(name = "location" , length = 100 , nullable = false)
    private String location;

    @Column(name = "contact" , length = 10 , nullable = false)
    private String contact;

    @OneToMany(mappedBy = "theatre")
    private Set<Screens> screens = new HashSet<>();

    @OneToMany(mappedBy = "theatre", cascade = CascadeType.ALL)
    private Set<Show> shows = new HashSet<>();


    @ManyToOne
    @JoinColumn(name = "TheatreUserOwner_id")
    private User user;


}
