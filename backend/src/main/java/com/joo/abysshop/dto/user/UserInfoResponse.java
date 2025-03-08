package com.joo.abysshop.dto.user;

public record UserInfoResponse(
    Long userId,
    Long cartId,
    String username,
    String nickname,
    String userType,
    Long points
) {

    public static UserInfoResponse of(
        Long userId,
        Long cartId,
        String username,
        String nickname,
        String userType,
        Long points) {
        return new UserInfoResponse(userId, cartId, username, nickname, userType, points);
    }
}
