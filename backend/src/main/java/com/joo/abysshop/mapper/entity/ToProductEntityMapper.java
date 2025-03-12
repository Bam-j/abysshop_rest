package com.joo.abysshop.mapper.entity;

import com.joo.abysshop.dto.admin.CreateProductRequest;
import com.joo.abysshop.entity.admin.AddProductEntity;
import com.joo.abysshop.entity.admin.AddProductImageEntity;

public interface ToProductEntityMapper {

    ProductEntity toProductEntity(CreateProductRequest createProductRequest);

    AddProductEntity toAddProductEntity(CreateProductRequest createProductRequest);

    AddProductImageEntity toAddProductImageEntity(String originalFileName, Long productId);
}
