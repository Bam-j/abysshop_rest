package com.joo.abysshop.entity.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserEntity {

    private Long userId;
    private String username;
    private String nickname;
    private String password;
    private String userType;
    private Long points;
}
