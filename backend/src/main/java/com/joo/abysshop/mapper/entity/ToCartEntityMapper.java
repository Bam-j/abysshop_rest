package com.joo.abysshop.mapper.entity;

import com.joo.abysshop.dto.cart.AddItemToCartRequest;
import com.joo.abysshop.dto.product.ProductInfoRequest;

public interface ToCartEntityMapper {

    AddCartItemEntity toAddCartItemEntity(AddItemToCartRequest addItemToCartRequest,
        ProductInfoRequest productInfoRequest);
}
