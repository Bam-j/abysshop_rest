package com.joo.abysshop.dto.account;

public record WithdrawAccountRequest(Long userId, String password) {

    public static WithdrawAccountRequest of(Long userId, String password) {
        return new WithdrawAccountRequest(userId, password);
    }
}
