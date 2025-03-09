package com.joo.abysshop.dto.account;

public record AccountSignUpResponse(String username, String nickname) {

    public static AccountSignUpResponse of(String username, String nickname) {
        return new AccountSignUpResponse(username, nickname);
    }
}
