package com.example.userauth.Config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;

@Getter
@Setter
@Service
public class JwtService {
    private String jwtkey = "thisisthemostSecretkeyforjwttoken012";
    private int jwtExpiration = 86400000;


//
    public String generateJwtToken(Authentication authentication){
        return null;
    }
}
