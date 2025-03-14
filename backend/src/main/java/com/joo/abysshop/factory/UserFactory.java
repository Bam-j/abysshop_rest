package com.joo.abysshop.factory;

import com.joo.abysshop.dto.account.SignUpRequest;
import com.joo.abysshop.entity.user.User;

public class UserFactory {

    public static User of(SignUpRequest signUpRequest, String encryptedPassword) {
        return User.builder()
            .username(signUpRequest.username())
            .nickname(signUpRequest.nickname())
            .password(encryptedPassword)
            .pointBalance(0L)
            .build();
    }
}
