package com.joo.abysshop.controller.product;

import com.joo.abysshop.dto.product.response.ProductDetailResponse;
import com.joo.abysshop.service.product.ProductQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
