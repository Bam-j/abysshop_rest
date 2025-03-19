package com.joo.abysshop.service.account;

import com.joo.abysshop.dto.account.request.SignInRequest;
import com.joo.abysshop.entity.user.User;
import com.joo.abysshop.repository.cart.CartRepository;
import com.joo.abysshop.repository.user.UserRepository;
import com.joo.abysshop.util.exception.InvalidPasswordException;
import com.joo.abysshop.util.security.JwtUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthQueryService {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public String authenticateUser(SignInRequest signInRequest) {
        User user = userRepository.findByUsername(signInRequest.username())
            .orElseThrow(() -> new EntityNotFoundException("계정이 존재하지 않습니다."));

        String encodedPassword = user.getPassword();

        if (!passwordEncoder.matches(encodedPassword, signInRequest.password())) {
            throw new InvalidPasswordException("비밀번호가 일치하지 않습니다.");
        }

        Long cartId = cartRepository.findCartIdByUserId(user.getUserId());
        return jwtUtil.generateToken(user, cartId);
    }
}
