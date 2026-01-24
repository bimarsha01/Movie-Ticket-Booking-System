package com.example.userauth.Models;


import com.example.userauth.Models.Enums.EGenre;
import com.example.userauth.Models.Enums.EPayment;
import com.example.userauth.Models.Enums.EStatus;
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
public class Booking extends BaseCreatedAndUpdatedAt{
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status" , nullable = false)
    private EStatus eStatus ;


    @OneToMany(mappedBy = "booking")
    private Set<ShowSeat> seats = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "show_id" , nullable = false)
    private Show show;

     @ManyToOne
    @JoinColumn(name = "user_id" , nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private EPayment paymentMethod = EPayment.Credit_card;

    private LocalDateTime bookingTime;

    private Double totalPrice;



}
// id, user_id, show_id, status (PENDING, CONFIRMED, EXPIRED)

