package com.joo.abysshop.service.security;

import com.joo.abysshop.entity.security.JwtBlacklist;
import com.joo.abysshop.repository.security.JwtBlacklistRepository;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtBlacklistService {

    private final JwtBlacklistRepository jwtBlacklistRepository;

    public void addToBlacklist(String token, long expirationMillis) {
        LocalDateTime expiresAt = LocalDateTime.ofInstant(
            Instant.now().plusMillis(expirationMillis),
            ZoneId.systemDefault()
        );

        JwtBlacklist blacklistEntry = JwtBlacklist.builder()
            .token(token)
            .expiresAt(expiresAt)
            .build();
        jwtBlacklistRepository.save(blacklistEntry);
    }

    public boolean isTokenBlacklisted(String token) {
        return jwtBlacklistRepository.existsByToken(token);
    }
}
