package com.joo.abysshop.service.account;

import com.joo.abysshop.dto.account.request.SignUpRequest;
import com.joo.abysshop.entity.user.User;
import com.joo.abysshop.factory.UserFactory;
import com.joo.abysshop.repository.user.UserRepository;
import com.joo.abysshop.util.exception.DuplicateNicknameException;
import com.joo.abysshop.util.exception.DuplicateUsernameException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthCommandService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void createUser(SignUpRequest signUpRequest) {
        userRepository.findByUsername(signUpRequest.username())
            .ifPresent(user -> {
                throw new DuplicateUsernameException("이미 존재하는 계정입니다.");
            });

        userRepository.findByNickname(signUpRequest.nickname())
            .ifPresent(user -> {
                throw new DuplicateNicknameException("이미 사용중인 닉네임입니다.");
            });

        String encryptedPassword = passwordEncoder.encode(signUpRequest.password());

        User newUser = UserFactory.of(signUpRequest, encryptedPassword);
        userRepository.save(newUser);
    }
}
