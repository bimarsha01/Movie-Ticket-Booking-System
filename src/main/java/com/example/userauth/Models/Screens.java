package com.example.userauth.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Screens {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "screen_no", nullable = false)
    private Integer screenNo;

    @Column(name = "total_row", nullable = false)
    private Integer totalRows;

    @Column(name = "seat_per_row", nullable = false)
    private Integer seatsPerRow;

    @ManyToOne
    @JoinColumn(name = "theatre_id", nullable = false)
    private Theatre theatre;

    @OneToMany(mappedBy = "screens", cascade = CascadeType.ALL)
    private Set<Show> shows = new HashSet<>();

    @OneToMany(mappedBy = "screen", cascade = CascadeType.ALL)
    private Set<Seat>  seats = new HashSet<>();


}
//Screen: id, screen_number, total_seats, theater_id (Many-to-One)