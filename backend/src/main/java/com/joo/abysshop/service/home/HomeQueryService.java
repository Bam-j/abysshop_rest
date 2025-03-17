package com.joo.abysshop.service.home;

import com.joo.abysshop.dto.cart.response.CartResponse;
import com.joo.abysshop.dto.product.response.ProductListResponse;
import com.joo.abysshop.service.cart.CartQueryService;
import com.joo.abysshop.service.product.ProductQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HomeQueryService {

    private final ProductQueryService productQueryService;
    private final CartQueryService cartQueryService;

    public Page<ProductListResponse> getPagedProductList(Pageable pageable) {
        return productQueryService.getPagedProductList(pageable);
    }

    public CartResponse getCart(Long userId) {
        return cartQueryService.getCartByUserId(userId);
    }
}
