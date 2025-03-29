package com.joo.abysshop.service.user;

import com.joo.abysshop.entity.user.User;
import com.joo.abysshop.repository.user.UserRepository;
import com.joo.abysshop.util.exception.auth.InvalidUsernameException;
import com.joo.abysshop.util.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new InvalidUsernameException("계정이 존재하지 않습니다."));

        return new CustomUserDetails(
            user.getUserId(),
            user.getCart().getCartId(),
            user.getUsername(),
            user.getNickname(),
            user.getPassword(),
            user.getUserType().name(),
            user.getPointBalance(),
            Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_" + user.getUserType().name())
            )
        );
    }
}
