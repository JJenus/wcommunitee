package com.predictz.winningcommunitee.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.predictz.winningcommunitee.model.AppUser;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {
    private String secret = "#&6t37hdj$fd09!!!56% 343";

    public String generateToken(AppUser user){
        Date now = new Date();
        Long seconds = 604800L; // 7 days

        String token = JWT.create().withSubject(user.getName())
                .withIssuer("Community Winners")
                .withSubject("Login Auth")
                .withClaim("email", user.getEmail())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(now.getTime() + seconds))
                .sign(Algorithm.HMAC256(secret));

        return token;
    }

    public String verifyToken(String token)throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withIssuer("Community Winners")
                .withSubject("Login Auth")
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("email").asString();
    }
}
