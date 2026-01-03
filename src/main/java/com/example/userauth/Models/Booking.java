package com.example.userauth.Models;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Booking extends BaseCreatedAndUpdatedAt{
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "show_id" , nullable = false)
    private Show show;

     @ManyToOne
    @JoinColumn(name = "user_id" , nullable = false)
    private User user;





}
// id, user_id, show_id, status (PENDING, CONFIRMED, EXPIRED)

