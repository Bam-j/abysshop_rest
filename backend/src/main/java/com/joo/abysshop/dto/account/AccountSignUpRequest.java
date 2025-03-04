package com.joo.abysshop.dto.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class AccountSignUpRequest {

    private String username;
    private String nickname;
    private String password;
}
