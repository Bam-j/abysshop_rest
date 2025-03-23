package com.joo.abysshop.service.account;

import com.joo.abysshop.dto.account.request.WithdrawAccountRequest;
import com.joo.abysshop.dto.user.request.UpdateNicknameRequest;
import com.joo.abysshop.dto.account.request.UpdatePasswordRequest;
import com.joo.abysshop.entity.user.User;
import com.joo.abysshop.repository.user.UserRepository;
import com.joo.abysshop.service.security.JwtBlacklistService;
import com.joo.abysshop.util.exception.InvalidPasswordException;
import com.joo.abysshop.util.security.JwtUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountCommandService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final JwtBlacklistService jwtBlacklistService;

    @Transactional
    public void updateNickname(UpdateNicknameRequest updateNicknameRequest) {
        User user = userRepository.findById(updateNicknameRequest.userId())
            .orElseThrow(() -> new EntityNotFoundException("회원을 찾을 수 없습니다."));

        user.updateNickname(updateNicknameRequest.newNickname());
    }

    @Transactional
    public void updatePassword(UpdatePasswordRequest updatePasswordRequest) {
        User user = userRepository.findById(updatePasswordRequest.userId())
            .orElseThrow(() -> new EntityNotFoundException("회원을 찾을 수 없습니다."));

        String encryptedNewPassword = passwordEncoder.encode(updatePasswordRequest.newPassword());
        user.updatePassword(encryptedNewPassword);
    }

    @Transactional
    public void withdraw(WithdrawAccountRequest withdrawAccountRequest, String token) {
        User user = userRepository.findById(withdrawAccountRequest.userId())
            .orElseThrow(() -> new EntityNotFoundException("회원을 찾을 수 없습니다."));
        String encodedPassword = user.getPassword();

        if (!passwordEncoder.matches( withdrawAccountRequest.password(), encodedPassword)) {
            throw new InvalidPasswordException("비밀번호가 일치하지 않습니다.");
        }

        userRepository.delete(user);

        long remainingExpiration = jwtUtil.extractExpiration(token) - System.currentTimeMillis();
        jwtBlacklistService.addToBlacklist(token, remainingExpiration);
    }
}
