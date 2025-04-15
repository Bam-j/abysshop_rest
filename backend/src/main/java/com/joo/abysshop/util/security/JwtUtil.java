package com.joo.abysshop.util.security;

import com.joo.abysshop.entity.user.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
@Slf4j
public class JwtUtil {

    private final Key key;
    @Getter
    private final long expiration;

    public JwtUtil(
        @Value("${jwt.secret}")
        String secretKey,
        @Value("${jwt.expiration}")
        long expiration
    ) {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
        this.expiration = expiration;
    }

    public String generateToken(User user, Long cartId) {
        return Jwts.builder()
            .setSubject(user.getUsername())
            .claim("userId", user.getUserId())
            .claim("cartId", cartId)
            .claim("nickname", user.getNickname())
            .claim("userType", user.getUserType().name())
            .claim("pointBalance", user.getPointBalance())
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + expiration))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        try {
            String usernameFromToken = extractUsername(token);
            return usernameFromToken.equals(userDetails.getUsername()) && !isTokenExpired(token);
        } catch (Exception e) {
            log.error("Token validation error", e);
            return false;
        }
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody();
    }
}
