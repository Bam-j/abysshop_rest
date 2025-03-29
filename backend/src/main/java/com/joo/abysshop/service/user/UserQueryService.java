package com.joo.abysshop.service.user;

import com.joo.abysshop.dto.user.response.UserInfoResponse;
import com.joo.abysshop.entity.user.User;
import com.joo.abysshop.repository.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserQueryService {

    private final UserRepository userRepository;

    public UserInfoResponse getUserInfo(Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 회원입니다."));

        return UserInfoResponse.of(
            user.getUserId(),
            user.getCart().getCartId(),
            user.getUsername(),
            user.getNickname(),
            user.getUserType().name(),
            user.getPointBalance()
        );
    }
}
