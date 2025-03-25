package com.joo.abysshop.dto.point.request;

public record DeductPointsRequest(Long userId, Long orderTotalPrice) {

    public static DeductPointsRequest of(Long userId, Long orderTotalPrice) {
        return new DeductPointsRequest(userId, orderTotalPrice);
    }
}
