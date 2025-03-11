package com.joo.abysshop.dto.point;

public record CreatePointRechargeRequest(Long userId, Long points) {

    public static CreatePointRechargeRequest of(Long userId, Long points) {
        return new CreatePointRechargeRequest(userId, points);
    }
}
