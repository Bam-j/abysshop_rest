package com.joo.abysshop.dto.account;

public record AccountSignInResponse(String password) {

    public static AccountSignInResponse of(String password) {
        return new AccountSignInResponse(password);
    }
}
