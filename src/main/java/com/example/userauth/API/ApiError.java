package com.example.userauth.API;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {

        private String error;
        private Boolean errorCode;
        private Object data;


}
