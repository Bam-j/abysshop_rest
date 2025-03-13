package com.joo.abysshop.service.account;

import com.joo.abysshop.dto.account.SignInRequest;
import com.joo.abysshop.dto.account.SignUpRequest;
import com.joo.abysshop.entity.user.User;
import com.joo.abysshop.repository.cart.CartRepository;
import com.joo.abysshop.repository.user.UserRepository;
import com.joo.abysshop.util.exception.InvalidPasswordException;
import com.joo.abysshop.util.security.JwtUtil;
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

        if (!user.getPassword().equals(signInRequest.password())) {
            throw new InvalidPasswordException("비밀번호가 일치하지 않습니다.");
        }

        Long cartId = cartRepository.findCartIdByUserId(user.getUserId());
        return jwtUtil.generateToken(user, cartId);
    }

    public String createUser(SignUpRequest signUpRequest) {
        /*
         *  1. users_table에 username, nickname이 존재하는지 확인 > 실패: DUPLICATE_USERNAME, DUPLICATE_NICKNAME
         *  2. 1번 통과시 users_table에 새 회원 create
         *  !저장시 비밀번호 암호화 작업
         */
    }
}
