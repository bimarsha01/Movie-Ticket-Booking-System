package com.example.userauth.Config;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import com.example.userauth.Helpers.userDetailsImpl;

import java.security.PrivateKey;
import java.util.Date;

@Getter
@Setter
@Service
public class JwtService {
    private String jwtkey = "thisisthemostSecretkeyforjwttoken012";
    private int jwtExpiration = 86400000;

//
    public String generateJwtToken(Authentication authentication){
    userDetailsImpl userprincipal = (userDetailsImpl) authentication.getPrincipal();
    return Jwts.builder()
            .setSubject(userprincipal.getUsername())
            .setIssuedAt(new Date())
            .setExpiration(new Date((new Date()).getTime() + jwtExpiration))
            .signWith(SignatureAlgorithm.HS512, jwtkey)
            .compact();
    }

    public boolean validateJwtToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(jwtkey)
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtkey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }



}
