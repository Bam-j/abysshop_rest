package com.joo.abysshop.mapper.dto;

import com.joo.abysshop.dto.product.ProductDetailResponse;
import com.joo.abysshop.dto.product.ProductInfoRequest;
import com.joo.abysshop.dto.product.ProductListResponse;

public interface ToProductDTOMapper {

    ProductListResponse toProductListResponse(ProductEntity productEntity);

    ProductListResponse toProductListResponseWithImage(ProductEntity productEntity,
        String originalFilename);

    ProductDetailResponse toProductDetailResponse(ProductEntity productEntity,
        String originalFileName);

    ProductInfoRequest toProductInfoRequest(ProductEntity productEntity);
}
