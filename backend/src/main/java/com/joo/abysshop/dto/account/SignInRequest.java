package com.joo.abysshop.dto.account;

public record SignInRequest(String username, String password) {

    public static SignInRequest of(String username, String password) {
        return new SignInRequest(username, password);
    }
}
