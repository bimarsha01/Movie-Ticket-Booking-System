package com.example.userauth.Config;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import com.example.userauth.Helpers.userDetailsImpl;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.PrivateKey;
import java.util.Date;
@Slf4j
@Getter
@Setter
@Service
public class JwtService {
    private String jwtkey = "thisIsASecretKeyThatIsAtLeast64CharactersLongToSatisfyHS512AlgorithmRequirements12345";
    private int jwtExpiration = 86400000;

    private Key getSigningKey() {
        byte[] keyBytes = this.jwtkey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateJwtToken(Authentication authentication){
        log.info("this is generating the token");
        userDetailsImpl userPrincipal = (userDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    public boolean validateJwtToken(String token) {
        log.info("this is validating the token");
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            log.error("JWT validation failed: {}", e.getMessage());
            return false;
        }
    }

    public String getUserNameFromJwtToken(String token) {
        log.info("getting the username from the jwt token");
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}