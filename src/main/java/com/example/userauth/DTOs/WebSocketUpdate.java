package com.example.userauth.DTOs;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WebSocketUpdate {
    private String status;
    private List<String> seatIds;

}
