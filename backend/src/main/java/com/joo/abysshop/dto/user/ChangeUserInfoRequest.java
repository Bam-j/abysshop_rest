package com.joo.abysshop.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ChangeUserInfoRequest {

    private Integer userId;
    private String nickname;
    private String password;
}
