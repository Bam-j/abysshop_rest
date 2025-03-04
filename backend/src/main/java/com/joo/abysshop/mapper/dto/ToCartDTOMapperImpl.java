package com.joo.abysshop.mapper.dto;

import com.joo.abysshop.dto.cart.AddItemRequest;
import com.joo.abysshop.dto.cart.CartItemResponse;
import com.joo.abysshop.dto.cart.CartResponse;
import com.joo.abysshop.dto.cart.RemoveItemRequest;
import com.joo.abysshop.dto.cart.UpdateQuantityRequest;
import com.joo.abysshop.entity.cart.CartEntity;
import com.joo.abysshop.entity.cart.CartItemEntity;
import org.springframework.stereotype.Component;

@Component
public class ToCartDTOMapperImpl implements ToCartDTOMapper {

    @Override
    public CartResponse toCartResponse(CartEntity cartEntity) {
        return CartResponse.builder()
            .cartId(cartEntity.getCartId())
            .userId(cartEntity.getUserId())
            .quantity(cartEntity.getQuantity())
            .totalPoints(cartEntity.getTotalPoints())
            .build();
    }

    @Override
    public CartItemResponse toCartItemResponse(CartItemEntity cartItemEntity) {
        return CartItemResponse.builder()
            .cartItemId(cartItemEntity.getCartItemId())
            .cartId(cartItemEntity.getCartId())
            .productId(cartItemEntity.getProductId())
            .productName(cartItemEntity.getProductName())
            .price(cartItemEntity.getPrice())
            .quantity(cartItemEntity.getQuantity())
            .build();
    }

    @Override
    public AddItemRequest toAddItemRequest(UpdateQuantityRequest updateQuantityRequest) {
        return AddItemRequest.builder()
            .cartId(updateQuantityRequest.getCartId())
            .productId(updateQuantityRequest.getProductId())
            .build();
    }

    @Override
    public RemoveItemRequest toRemoveItemRequest(UpdateQuantityRequest updateQuantityRequest) {
        return RemoveItemRequest.builder()
            .cartId(updateQuantityRequest.getCartId())
            .productId(updateQuantityRequest.getProductId())
            .userId(updateQuantityRequest.getUserId())
            .build();
    }
}
