package com.joo.abysshop.factory;

import com.joo.abysshop.dto.account.request.SignUpRequest;
import com.joo.abysshop.entity.user.User;
import com.joo.abysshop.util.enums.UserType;

public class UserFactory {

    public static User of(SignUpRequest signUpRequest, String encryptedPassword) {
        return User.builder()
            .username(signUpRequest.username())
            .nickname(signUpRequest.nickname())
            .password(encryptedPassword)
            .userType(UserType.USER)
            .pointBalance(0L)
            .build();
    }

    public static User ofAdmin(String username, String password) {
        return User.builder()
            .username(username)
            .nickname("testadmin")
            .password(password)
            .userType(UserType.ADMIN)
            .pointBalance(0L)
            .build();
    }
}
