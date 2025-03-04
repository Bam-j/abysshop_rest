package com.joo.abysshop.mapper.entity;

import com.joo.abysshop.dto.cart.AddItemRequest;
import com.joo.abysshop.dto.product.ProductInfoRequest;
import com.joo.abysshop.entity.cart.AddCartItemEntity;
import org.springframework.stereotype.Component;

@Component
public class ToCartEntityMapperImpl implements ToCartEntityMapper {

    @Override
    public AddCartItemEntity toAddCartItemEntity(AddItemRequest addItemRequest,
        ProductInfoRequest productInfoRequest) {
        return AddCartItemEntity.builder()
            .cartId(addItemRequest.getCartId())
            .productId(addItemRequest.getProductId())
            .productName(productInfoRequest.getProductName())
            .price(productInfoRequest.getPrice())
            .build();
    }
}
