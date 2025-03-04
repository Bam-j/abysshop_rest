package com.joo.abysshop.mapper.entity;

import com.joo.abysshop.dto.admin.AddProductRequest;
import com.joo.abysshop.entity.admin.AddProductEntity;
import com.joo.abysshop.entity.admin.AddProductImageEntity;
import com.joo.abysshop.entity.product.ProductEntity;

public interface ToProductEntityMapper {

    ProductEntity toProductEntity(AddProductRequest addProductRequest);

    AddProductEntity toAddProductEntity(AddProductRequest addProductRequest);

    AddProductImageEntity toAddProductImageEntity(String originalFileName, Long productId);
}
