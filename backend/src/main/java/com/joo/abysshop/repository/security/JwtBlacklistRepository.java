package com.joo.abysshop.repository.security;

import com.joo.abysshop.entity.security.JwtBlacklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JwtBlacklistRepository extends JpaRepository<JwtBlacklist, Long> {

    boolean existsByToken(String token);
}
