package com.joo.abysshop.dto.account;

public record AccountSignUpRequest(String username, String nickname, String password) {

    public static AccountSignUpRequest of(String username, String nickname, String password) {
        return new AccountSignUpRequest(username, nickname, password);
    }
}
