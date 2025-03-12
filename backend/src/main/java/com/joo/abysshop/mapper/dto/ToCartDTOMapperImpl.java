package com.joo.abysshop.mapper.dto;

import com.joo.abysshop.dto.cart.AddItemToCartRequest;
import com.joo.abysshop.dto.cart.CartItemResponse;
import com.joo.abysshop.dto.cart.CartResponse;
import com.joo.abysshop.dto.cart.DeleteItemFromCartRequest;
import com.joo.abysshop.dto.cart.UpdateQuantityRequest;
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
    public AddItemToCartRequest toAddItemRequest(UpdateQuantityRequest updateQuantityRequest) {
        return AddItemToCartRequest.builder()
            .cartId(updateQuantityRequest.getCartId())
            .productId(updateQuantityRequest.getProductId())
            .build();
    }

    @Override
    public DeleteItemFromCartRequest toRemoveItemRequest(UpdateQuantityRequest updateQuantityRequest) {
        return DeleteItemFromCartRequest.builder()
            .cartId(updateQuantityRequest.getCartId())
            .productId(updateQuantityRequest.getProductId())
            .userId(updateQuantityRequest.getUserId())
            .build();
    }
}
