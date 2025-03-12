package com.joo.abysshop.service.admin;

import com.joo.abysshop.dto.admin.ProvidePointRequest;
import com.joo.abysshop.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminPointService {

    private final UserRepository userRepository;


    public void providePoints(ProvidePointRequest providePointRequest) {
        /*
         *  1. userId로 유저 탐색 후 해당 유저 포인트에 +
         */
    }
}
