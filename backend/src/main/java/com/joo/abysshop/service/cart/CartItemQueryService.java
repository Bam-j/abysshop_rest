package com.joo.abysshop.service.cart;

import com.joo.abysshop.dto.cart.response.CartItemResponse;
import com.joo.abysshop.repository.cart.CartItemRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CartItemQueryService {

    private final CartItemRepository cartItemRepository;

    public List<CartItemResponse> getCartItemList(Long cartId) {
        return cartItemRepository.findAllByCartId(cartId)
            .stream()
            .map(CartItemResponse::of)
            .collect(Collectors.toList());
    }

}
