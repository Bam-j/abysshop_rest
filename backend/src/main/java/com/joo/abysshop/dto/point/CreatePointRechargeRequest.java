package com.joo.abysshop.dto.point;

public record CreatePointRechargeRequest(Long userId, String nickname, Long points) {

    public static CreatePointRechargeRequest of(Long userId, String nickname, Long points) {
        return new CreatePointRechargeRequest(userId, nickname, points);
    }
}
