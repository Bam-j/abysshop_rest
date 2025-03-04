package com.joo.abysshop.dto.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ChangePasswordRequest {

    private Long userId;
    private String newPassword;
}
