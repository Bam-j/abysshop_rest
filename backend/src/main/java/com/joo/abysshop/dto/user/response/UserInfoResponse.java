package com.joo.abysshop.dto.user.response;

public record UserInfoResponse(
    Long userId,
    Long cartId,
    String username,
    String nickname,
    String userType,
    Long pointBalance
) {

    public static UserInfoResponse of(
        Long userId,
        Long cartId,
        String username,
        String nickname,
        String userType,
        Long pointBalance) {
        return new UserInfoResponse(userId, cartId, username, nickname, userType, pointBalance);
    }
}
