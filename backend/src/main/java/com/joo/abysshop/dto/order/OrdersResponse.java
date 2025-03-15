package com.joo.abysshop.dto.order;

import java.util.List;
import org.springframework.data.domain.Page;

public record OrdersResponse(
    List<AdminOrderListResponse> orders,
    int currentPage,
    int totalPages,
    long totalElements
) {

    public static OrdersResponse of(Page<AdminOrderListResponse> orderPage) {
        return new OrdersResponse(
            orderPage.getContent(),
            orderPage.getNumber(),
            orderPage.getTotalPages(),
            orderPage.getTotalElements()
        );
    }
}
