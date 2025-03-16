package com.joo.abysshop.service.cart;

import com.joo.abysshop.dto.cart.request.AddItemToCartRequest;
import com.joo.abysshop.entity.cart.Cart;
import com.joo.abysshop.repository.cart.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CartItemCommandService {

    private final CartItemRepository cartItemRepository;

    @Transactional
    public void clearCartItems(Long cartId) {
        cartItemRepository.deleteAllByCartId(cartId);
    }

    public void addOrUpdateCartItem(AddItemToCartRequest addItemToCartRequest) {
    }
}
