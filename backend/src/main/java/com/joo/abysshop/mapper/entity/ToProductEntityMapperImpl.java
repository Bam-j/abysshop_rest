package com.joo.abysshop.mapper.entity;


import com.joo.abysshop.dto.admin.CreateProductRequest;
import com.joo.abysshop.entity.admin.AddProductEntity;
import com.joo.abysshop.entity.admin.AddProductImageEntity;
import org.springframework.stereotype.Component;

@Component
public class ToProductEntityMapperImpl implements ToProductEntityMapper {

    @Override
    public ProductEntity toProductEntity(CreateProductRequest createProductRequest) {
        return ProductEntity.builder()
            .productName(createProductRequest.getProductName())
            .price(createProductRequest.getPrice())
            .description(createProductRequest.getDescription())
            .build();
    }

    @Override
    public AddProductEntity toAddProductEntity(CreateProductRequest createProductRequest) {
        return AddProductEntity.builder()
            .productName(createProductRequest.getProductName())
            .price(createProductRequest.getPrice())
            .description(createProductRequest.getDescription())
            .build();
    }

    @Override
    public AddProductImageEntity toAddProductImageEntity(String originalFileName,
        Long productId) {
        return AddProductImageEntity.builder()
            .productId(productId)
            .originalFileName(originalFileName)
            .build();
    }
}
