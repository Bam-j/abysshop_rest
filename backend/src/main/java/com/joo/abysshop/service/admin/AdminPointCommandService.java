package com.joo.abysshop.service.admin;

import com.joo.abysshop.dto.admin.ProvidePointRequest;
import com.joo.abysshop.entity.user.User;
import com.joo.abysshop.service.user.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminPointCommandService {

    private final UserQueryService userQueryService;

    @Transactional
    public void providePoints(ProvidePointRequest providePointRequest) {
        User user = userQueryService.findById(providePointRequest.userId());
        user.updatePointBalance(providePointRequest.points());
    }
}
