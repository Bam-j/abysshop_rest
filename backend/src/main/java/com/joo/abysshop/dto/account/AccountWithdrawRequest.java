package com.joo.abysshop.dto.account;

public record AccountWithdrawRequest(Long userId, String password) {

    public static AccountWithdrawRequest of(Long userId, String password) {
        return new AccountWithdrawRequest(userId, password);
    }
}
