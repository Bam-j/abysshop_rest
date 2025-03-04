package com.joo.abysshop.mapper.entity;


import com.joo.abysshop.dto.admin.AddProductRequest;
import com.joo.abysshop.entity.admin.AddProductEntity;
import com.joo.abysshop.entity.admin.AddProductImageEntity;
import com.joo.abysshop.entity.product.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ToProductEntityMapperImpl implements ToProductEntityMapper {

    @Override
    public ProductEntity toProductEntity(AddProductRequest addProductRequest) {
        return ProductEntity.builder()
            .productName(addProductRequest.getProductName())
            .price(addProductRequest.getPrice())
            .description(addProductRequest.getDescription())
            .build();
    }

    @Override
    public AddProductEntity toAddProductEntity(AddProductRequest addProductRequest) {
        return AddProductEntity.builder()
            .productName(addProductRequest.getProductName())
            .price(addProductRequest.getPrice())
            .description(addProductRequest.getDescription())
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
