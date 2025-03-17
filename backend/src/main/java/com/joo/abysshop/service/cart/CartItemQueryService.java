package com.joo.abysshop.service.cart;

import com.joo.abysshop.dto.cart.response.CartItemDetailResponse;
import com.joo.abysshop.dto.cart.response.CartItemResponse;
import com.joo.abysshop.repository.cart.CartItemRepository;
import com.joo.abysshop.service.product.ProductQueryService;
import jakarta.persistence.EntityNotFoundException;
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
    private final ProductQueryService productQueryService;

    public List<CartItemResponse> getCartItemList(Long cartId) {
        return cartItemRepository.findAllByCartId(cartId)
            .stream()
            .map(CartItemResponse::of)
            .collect(Collectors.toList());
    }

    public CartItemDetailResponse getCartItemDetail(Long productId) {
        Long quantity = cartItemRepository.findQuantityByProductId(productId);
        if (quantity == null) {
            throw new EntityNotFoundException("해당 상품이 장바구니에 존재하지 않습니다.");
        }

        Long price = productQueryService.findPriceById(productId);
        if (price == null) {
            throw new EntityNotFoundException("상품 가격 정보를 찾을 수 없습니다.");
        }

        return new CartItemDetailResponse(quantity, price);
    }
}
