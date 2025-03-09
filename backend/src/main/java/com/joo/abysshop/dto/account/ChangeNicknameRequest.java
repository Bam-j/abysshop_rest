package com.joo.abysshop.dto.account;

public record ChangeNicknameRequest(Long userId, String newNickname) {

    public static ChangeNicknameRequest of(Long userId, String newNickname) {
        return new ChangeNicknameRequest(userId, newNickname);
    }
}
