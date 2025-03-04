package com.joo.abysshop.mapper.entity;

import com.joo.abysshop.dto.cart.AddItemRequest;
import com.joo.abysshop.dto.product.ProductInfoRequest;
import com.joo.abysshop.entity.cart.AddCartItemEntity;

public interface ToCartEntityMapper {

    AddCartItemEntity toAddCartItemEntity(AddItemRequest addItemRequest,
        ProductInfoRequest productInfoRequest);
}
