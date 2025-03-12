package com.joo.abysshop.mapper.entity;

import com.joo.abysshop.dto.cart.AddItemToCartRequest;
import com.joo.abysshop.dto.product.ProductInfoRequest;
import org.springframework.stereotype.Component;

@Component
public class ToCartEntityMapperImpl implements ToCartEntityMapper {

    @Override
    public AddCartItemEntity toAddCartItemEntity(AddItemToCartRequest addItemToCartRequest,
        ProductInfoRequest productInfoRequest) {
        return AddCartItemEntity.builder()
            .cartId(addItemToCartRequest.getCartId())
            .productId(addItemToCartRequest.getProductId())
            .productName(productInfoRequest.getProductName())
            .price(productInfoRequest.getPrice())
            .build();
    }
}
