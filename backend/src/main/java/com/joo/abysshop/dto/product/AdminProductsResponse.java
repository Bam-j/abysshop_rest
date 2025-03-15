package com.joo.abysshop.dto.product;

import java.util.List;
import org.springframework.data.domain.Page;

public record AdminProductsResponse(
    List<AdminProductListResponse> products,
    int currentPage,
    int totalPages,
    long totalElements
) {

    public static AdminProductsResponse of(Page<AdminProductListResponse> productPage) {
        return new AdminProductsResponse(
            productPage.getContent(),
            productPage.getNumber(),
            productPage.getTotalPages(),
            productPage.getTotalElements()
        );
    }
}
