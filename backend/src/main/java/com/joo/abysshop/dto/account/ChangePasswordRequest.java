package com.joo.abysshop.dto.account;

public record ChangePasswordRequest(Long userId, String newPassword) {

    public static ChangePasswordRequest of(Long userId, String newPassword) {
        return new ChangePasswordRequest(userId, newPassword);
    }
}
