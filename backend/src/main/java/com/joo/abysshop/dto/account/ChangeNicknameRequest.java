package com.joo.abysshop.dto.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ChangeNicknameRequest {

    private Long userId;
    private String newNickname;
}
