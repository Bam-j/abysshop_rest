package com.joo.abysshop.service.account;

import com.joo.abysshop.dto.account.SignInRequest;
import com.joo.abysshop.dto.account.SignUpRequest;
import com.joo.abysshop.entity.user.User;
import com.joo.abysshop.repository.cart.CartRepository;
import com.joo.abysshop.repository.user.UserRepository;
import com.joo.abysshop.util.exception.DuplicateNicknameException;
import com.joo.abysshop.util.exception.DuplicateUsernameException;
import com.joo.abysshop.util.exception.InvalidPasswordException;
import com.joo.abysshop.util.security.JwtUtil;
import com.joo.abysshop.util.security.PasswordSecurity;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final JwtUtil jwtUtil;

    public String authenticateUser(SignInRequest signInRequest) {
        User user = userRepository.findByUsername(signInRequest.username())
            .orElseThrow(() -> new EntityNotFoundException("계정이 존재하지 않습니다."));

        String encodedPassword = user.getPassword();

        if (!PasswordSecurity.matches(encodedPassword, signInRequest.password())) {
            throw new InvalidPasswordException("비밀번호가 일치하지 않습니다.");
        }

        Long cartId = cartRepository.findCartIdByUserId(user.getUserId());
        return jwtUtil.generateToken(user, cartId);
    }

    public void createUser(SignUpRequest signUpRequest) {
        userRepository.findByUsername(signUpRequest.username())
            .ifPresent(user -> {
                throw new DuplicateUsernameException("이미 존재하는 계정입니다.");
            });

        userRepository.findByNickname(signUpRequest.nickname())
            .ifPresent(user -> {
                throw new DuplicateNicknameException("이미 존재하는 닉네임입니다.");
            });

        String encryptedPassword = PasswordSecurity.encryptPassword(signUpRequest.password());

        User user = User.builder()
            .username(signUpRequest.username())
            .nickname(signUpRequest.nickname())
            .password(encryptedPassword)
            .pointBalance(0L)
            .build();
        userRepository.save(user);
    }
}
