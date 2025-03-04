package com.joo.abysshop.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserInfoResponse {

    private Long userId;
    private Long cartId;
    private String username;
    private String nickname;
    private String userType;
    private Long points;
}
