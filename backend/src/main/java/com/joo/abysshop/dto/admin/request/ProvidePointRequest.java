package com.joo.abysshop.dto.admin.request;

public record ProvidePointRequest(Long userId, Long points) {

    public static ProvidePointRequest of(Long userId, Long points) {
        return new ProvidePointRequest(userId, points);
    }
}
