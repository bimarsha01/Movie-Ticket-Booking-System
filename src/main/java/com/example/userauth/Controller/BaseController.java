package com.example.userauth.Controller;

import com.example.userauth.API.ApiResponse;

public class BaseController {
        public ApiResponse successResponse(String message , Boolean status , Object data){
            return new ApiResponse(message , status , data);

        }public ApiResponse failureResponse(String message , Boolean status , Object data){
            return new ApiResponse(message , status , data);
        }
    }

