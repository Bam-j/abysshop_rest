package com.joo.abysshop.util.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;

@Getter
public class CustomUserDetails implements UserDetails {

    private final Long userId;
    private final Long cartId;
    private final String username;
    private final String nickname;
    private final String password;
    private final String userType;
    private final Long pointBalance;

    private final Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(
        Long userId,
        Long cartId,
        String username,
        String nickname,
        String password,
        String userType,
        Long pointBalance,
        Collection<? extends GrantedAuthority> authorities
    ) {
        this.userId = userId;
        this.cartId = cartId;
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.userType = userType;
        this.pointBalance = pointBalance;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
