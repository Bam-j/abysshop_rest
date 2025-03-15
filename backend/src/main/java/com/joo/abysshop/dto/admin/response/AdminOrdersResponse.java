package com.joo.abysshop.dto.admin.response;

import java.util.List;
import org.springframework.data.domain.Page;

public record AdminOrdersResponse(
    List<AdminOrderListResponse> orders,
    int currentPage,
    int totalPages,
    long totalElements
) {

    public static AdminOrdersResponse of(Page<AdminOrderListResponse> orderPage) {
        return new AdminOrdersResponse(
            orderPage.getContent(),
            orderPage.getNumber(),
            orderPage.getTotalPages(),
            orderPage.getTotalElements()
        );
    }
}
