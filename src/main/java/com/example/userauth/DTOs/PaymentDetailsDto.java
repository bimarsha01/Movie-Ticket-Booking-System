package com.example.userauth.DTOs;

import com.example.userauth.Models.Enums.EPayment;
import lombok.Getter;

@Getter
public class PaymentDetailsDto {
    private Double price;
    private EPayment paymentMethod;
}
