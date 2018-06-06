package com.blackcrowsys.authservice.service;

import com.blackcrowsys.authservice.db.model.Login;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtSecurityTokeniser implements SecurityTokeniser {

    @Value("${security.jwt.key:Password1234}")
    private String key;

    @Override
    public String tokenise(Login login) {
        return Jwts.builder().setSubject(login.getUsername())
                .signWith(SignatureAlgorithm.HS512, key)
                .claim("Type", login.getUserType().name())
                .compact();
    }
}
