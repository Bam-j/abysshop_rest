package com.joo.abysshop.service.cart;

import com.joo.abysshop.entity.cart.Cart;
import com.joo.abysshop.repository.cart.CartRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CartCommandService {

    private final CartRepository cartRepository;
    private final CartQueryService cartQueryService;
    private final CartItemCommandService cartItemCommandService;

    @Transactional
    public void clearCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
            .orElseThrow(() -> new EntityNotFoundException("장바구니가 존재하지 않습니다."));
        cartItemCommandService.clearCartItems(cartId);
        cart.clearCart();
    }
}
