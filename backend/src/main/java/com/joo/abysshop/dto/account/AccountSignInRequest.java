package com.joo.abysshop.dto.account;

public record AccountSignInRequest(String username, String password) {

    public static AccountSignInRequest of(String username, String password) {
        return new AccountSignInRequest(username, password);
    }
}
