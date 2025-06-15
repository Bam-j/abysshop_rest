package com.joo.abysshop.controller.product;

import com.joo.abysshop.dto.cart.response.CartResponse;
import com.joo.abysshop.dto.home.HomeResponse;
import com.joo.abysshop.dto.product.response.ProductDetailResponse;
import com.joo.abysshop.dto.product.response.ProductListResponse;
import com.joo.abysshop.dto.product.response.ProductSearchResultResponse;
import com.joo.abysshop.dto.user.response.UserInfoResponse;
import com.joo.abysshop.service.product.ProductQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductQueryService productQueryService;

    @GetMapping("/{productId}")
    public ResponseEntity<Object> getProduct(@PathVariable("productId") Long productId) {
        ProductDetailResponse response = productQueryService.getProductDetail(productId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<ProductSearchResultResponse> searchProducts(Pageable pageable,
        @RequestParam("keyword") String keyword) {
        Page<ProductListResponse> pagedProductSearchList = productQueryService.getPagedProductSearchList(
            pageable, keyword);
        ProductSearchResultResponse response = new ProductSearchResultResponse(
            pagedProductSearchList);
        return ResponseEntity.ok(response);
    }
}
