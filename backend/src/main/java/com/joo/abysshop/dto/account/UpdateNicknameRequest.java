package com.joo.abysshop.dto.account;

public record UpdateNicknameRequest(Long userId, String newNickname) {

    public static UpdateNicknameRequest of(Long userId, String newNickname) {
        return new UpdateNicknameRequest(userId, newNickname);
    }
}
