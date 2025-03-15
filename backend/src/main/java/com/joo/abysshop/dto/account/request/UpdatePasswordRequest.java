package com.joo.abysshop.dto.account.request;

public record UpdatePasswordRequest(Long userId, String newPassword) {

    public static UpdatePasswordRequest of(Long userId, String newPassword) {
        return new UpdatePasswordRequest(userId, newPassword);
    }
}
