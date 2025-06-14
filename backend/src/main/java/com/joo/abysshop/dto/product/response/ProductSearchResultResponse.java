package com.joo.abysshop.dto.product.response;

import org.springframework.data.domain.Page;

public record ProductSearchResultResponse(
    Page<ProductListResponse> productSearchList
) {

    public static ProductSearchResultResponse of(Page<ProductListResponse> productSearchList) {
        return new ProductSearchResultResponse(productSearchList);
    }
}
