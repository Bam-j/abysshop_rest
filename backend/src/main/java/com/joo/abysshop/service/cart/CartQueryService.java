package com.joo.abysshop.service.cart;

import com.joo.abysshop.dto.cart.response.CartAndItemsResponse;
import com.joo.abysshop.dto.cart.response.CartItemResponse;
import com.joo.abysshop.dto.cart.response.CartResponse;
import com.joo.abysshop.entity.cart.Cart;
import com.joo.abysshop.entity.user.User;
import com.joo.abysshop.repository.cart.CartRepository;
import com.joo.abysshop.repository.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CartQueryService {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final CartItemQueryService cartItemQueryService;

    public CartResponse getCartByUserId(Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new EntityNotFoundException("계정이 존재하지 않습니다."));
        Cart cart = cartRepository.findByUser(user)
            .orElseThrow(() -> new EntityNotFoundException("장바구니가 존재하지 않습니다."));

        return CartResponse.of(cart);
    }

    public CartAndItemsResponse getCartAndItem(Long userId) {
        CartResponse cart = getCartByUserId(userId);
        List<CartItemResponse> cartItemList = cartItemQueryService.getCartItemList(cart.cartId());
        return CartAndItemsResponse.of(cart, cartItemList);
    }

    public Long getCartTotalQuantity(Long cartId) {
        return cartRepository.getTotalQuantityByCartId(cartId);
    }
}
