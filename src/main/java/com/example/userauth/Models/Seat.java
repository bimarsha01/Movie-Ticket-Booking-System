package com.example.userauth.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "seats", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"seat_no", "row_no", "screen_id"})
})

@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "seat_no")
    private Integer seatNo;

    @Column(name = "row_no")
    private Integer rowNo;

@ManyToOne
@JoinColumn(name = "screen_id" , nullable = false)
private Screens screen;


//    id, seat_number, row_number, screen_id (Many-to-One).
}
