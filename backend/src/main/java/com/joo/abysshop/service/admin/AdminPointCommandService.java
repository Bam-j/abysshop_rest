package com.joo.abysshop.service.admin;

import com.joo.abysshop.dto.admin.request.ProvidePointRequest;
import com.joo.abysshop.entity.user.User;
import com.joo.abysshop.repository.user.UserRepository;
import com.joo.abysshop.service.user.UserQueryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminPointCommandService {

    private final UserQueryService userQueryService;
    private final UserRepository userRepository;

    @Transactional
    public void providePoints(ProvidePointRequest providePointRequest) {
        User user = userRepository.findById(providePointRequest.userId())
            .orElseThrow(() -> new EntityNotFoundException("유저가 존재하지 않습니다."));
        user.updatePointBalance(providePointRequest.points());
    }
}
