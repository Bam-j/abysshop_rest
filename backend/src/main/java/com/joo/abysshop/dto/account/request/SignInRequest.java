package com.joo.abysshop.dto.account.request;

public record SignInRequest(String username, String password) {

    public static SignInRequest of(String username, String password) {
        return new SignInRequest(username, password);
    }
}
