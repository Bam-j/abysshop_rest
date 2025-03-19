package com.joo.abysshop.service.cart;

import com.joo.abysshop.dto.cart.response.CartItemDetailResponse;
import com.joo.abysshop.dto.cart.response.CartItemResponse;
import com.joo.abysshop.entity.cart.Cart;
import com.joo.abysshop.entity.product.Product;
import com.joo.abysshop.repository.cart.CartItemRepository;
import com.joo.abysshop.repository.cart.CartRepository;
import com.joo.abysshop.repository.product.ProductRepository;
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

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductQueryService productQueryService;

    public List<CartItemResponse> getCartItemList(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
            .orElseThrow(() -> new EntityNotFoundException("장바구니가 존재하지 않습니다."));
        return cartItemRepository.findAllByCart(cart)
            .stream()
            .map(CartItemResponse::of)
            .collect(Collectors.toList());
    }

    public CartItemDetailResponse getCartItemDetail(Long productId) {
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new EntityNotFoundException("상품이 존재하지 않습니다."));
        Long quantity = cartItemRepository.findQuantityByProduct(product);
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
