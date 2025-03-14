package com.joo.abysshop.util.security;

import com.joo.abysshop.entity.user.User;
import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Component
@Slf4j
public class JwtUtil {

    private final Key key;
    @Getter
    private final long expiration;

    public JwtUtil(Dotenv dotenv) {
        String secretKey = dotenv.get("JWT_SECRET");
        this.expiration = Long.parseLong(dotenv.get("JWT_EXPIRATION"));
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
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
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            log.error("JWT expired", e);
        } catch (Exception e) {
            log.error("JWT validation failed", e);
        }
        return false;
    }

    public long extractExpiration(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getExpiration()
            .getTime();
    }
}
