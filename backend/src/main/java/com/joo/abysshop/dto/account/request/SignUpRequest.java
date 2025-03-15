package com.joo.abysshop.dto.account.request;

public record SignUpRequest(String username, String nickname, String password) {

    public static SignUpRequest of(String username, String nickname, String password) {
        return new SignUpRequest(username, nickname, password);
    }
}
