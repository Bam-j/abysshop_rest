package com.joo.abysshop.dto.point.request;

public record CreatePointRechargeRequest(Long userId, Long requestedPoints) {

    public static CreatePointRechargeRequest of(Long userId, Long requestedPoints) {
        return new CreatePointRechargeRequest(userId, requestedPoints);
    }
}
