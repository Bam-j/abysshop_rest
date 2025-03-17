package com.joo.abysshop.dto.user.response;

import java.util.List;
import org.springframework.data.domain.Page;

public record UserOrdersResponse(
    List<UserOrderListResponse> orders,
    int currentPage,
    int totalPages,
    long totalElements
) {

    public static UserOrdersResponse of(Page<UserOrderListResponse> orderPage) {
        return new UserOrdersResponse(
            orderPage.getContent(),
            orderPage.getNumber(),
            orderPage.getTotalPages(),
            orderPage.getTotalElements()
        );
    }
}
