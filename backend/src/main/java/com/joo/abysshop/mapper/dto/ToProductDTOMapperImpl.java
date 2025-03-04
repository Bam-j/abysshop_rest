package com.joo.abysshop.mapper.dto;

import com.joo.abysshop.dto.product.ProductDetailResponse;
import com.joo.abysshop.dto.product.ProductInfoRequest;
import com.joo.abysshop.dto.product.ProductListResponse;
import com.joo.abysshop.entity.product.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ToProductDTOMapperImpl implements ToProductDTOMapper {

    @Override
    public ProductListResponse toProductListResponse(ProductEntity productEntity) {
        return ProductListResponse.builder()
            .productId(productEntity.getProductId())
            .productName(productEntity.getProductName())
            .price(productEntity.getPrice())
            .originalFileName("")
            .build();
    }

    @Override
    public ProductListResponse toProductListResponseWithImage(ProductEntity productEntity,
        String originalFilename) {
        return ProductListResponse.builder()
            .productId(productEntity.getProductId())
            .productName(productEntity.getProductName())
            .price(productEntity.getPrice())
            .originalFileName(originalFilename)
            .build();
    }

    @Override
    public ProductDetailResponse toProductDetailResponse(ProductEntity productEntity,
        String originalFileName) {
        return ProductDetailResponse.builder()
            .productId(productEntity.getProductId())
            .productName(productEntity.getProductName())
            .price(productEntity.getPrice())
            .description(productEntity.getDescription())
            .originalFileName(originalFileName)
            .build();
    }

    @Override
    public ProductInfoRequest toProductInfoRequest(ProductEntity productEntity) {
        return ProductInfoRequest.builder()
            .productName(productEntity.getProductName())
            .price(productEntity.getPrice())
            .build();
    }
}
