package com.epitech.pictmanager.shared.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    public String createToken(String id) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpiration);


        return Jwts.builder()
                .setSubject(id)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(this.getSigningKey())
                .compact();
    }

    public String getIdFromToken(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Claims claims =
                    Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(token).getBody();
            return !claims.getExpiration().before(new Date());
        } catch (JwtException e) {
            return false;
        }
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(this.jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }


}
