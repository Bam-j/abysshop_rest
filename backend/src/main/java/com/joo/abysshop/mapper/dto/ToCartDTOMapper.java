package com.joo.abysshop.mapper.dto;

import com.joo.abysshop.dto.cart.AddItemRequest;
import com.joo.abysshop.dto.cart.CartItemResponse;
import com.joo.abysshop.dto.cart.CartResponse;
import com.joo.abysshop.dto.cart.RemoveItemRequest;
import com.joo.abysshop.dto.cart.UpdateQuantityRequest;
import com.joo.abysshop.entity.cart.CartEntity;
import com.joo.abysshop.entity.cart.CartItemEntity;

public interface ToCartDTOMapper {

    CartResponse toCartResponse(CartEntity cartEntity);

    CartItemResponse toCartItemResponse(CartItemEntity cartItemEntity);

    AddItemRequest toAddItemRequest(UpdateQuantityRequest updateQuantityRequest);

    RemoveItemRequest toRemoveItemRequest(UpdateQuantityRequest updateQuantityRequest);
}
