package com.example.userauth.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "shows")

@Entity
public class Show extends BaseCreatedAndUpdatedAt{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Start_time" , nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time" , nullable = false)
    private LocalDateTime endTime;


    @Column(name = "price" , nullable = false)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "movie_id" , nullable = false)
    private Movies movies;

    @ManyToOne
    @JoinColumn(name = "theatre_id" , nullable = false)
    private Theatre theatre ;

    @ManyToOne
    @JoinColumn(name = "screen_id" , nullable = false)
    private Screens screens;

}
