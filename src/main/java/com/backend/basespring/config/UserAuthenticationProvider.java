package com.backend.basespring.config;

import com.backend.basespring.dto.LoginDto;
import com.backend.basespring.model.User;
import com.backend.basespring.service.AuthenticationService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;

@Component
public class UserAuthenticationProvider {
    @Value("${security.jwt.token.secret-key}")
    private String secretKey;
    private final AuthenticationService authenticationService;


    public UserAuthenticationProvider(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(ObjectId id) {
        Claims claims = Jwts.claims().setSubject(id.toString());

        Date now = new Date();
        Date validity = new Date(now.getTime() + 3600000);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
    public Authentication validateToken(String token) {
        String id = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        User user = authenticationService.findById(id);
        return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
    }
    public Authentication validateCredential(LoginDto loginDto) {
        User user = authenticationService.authenticate(loginDto);
        return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
    }
}
