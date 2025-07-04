package com.joo.abysshop.service.product;

import com.joo.abysshop.dto.admin.response.AdminProductListResponse;
import com.joo.abysshop.dto.product.response.ProductDetailResponse;
import com.joo.abysshop.dto.product.response.ProductListResponse;
import com.joo.abysshop.entity.product.Product;
import com.joo.abysshop.repository.product.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductQueryService {

    private final ProductRepository productRepository;

    public Page<AdminProductListResponse> getPagedAdminProductList(Pageable pageable) {
        return productRepository.findAll(pageable).map(AdminProductListResponse::new);
    }

    public Product findById(Long productId) {
        return productRepository.findById(productId)
            .orElseThrow(() -> new EntityNotFoundException("해당 상품이 존재하지 않습니다."));
    }

    public boolean existsByProductName(String productName) {
        return productRepository.existsByProductName(productName);
    }

    public Long findPriceById(Long productId) {
        return productRepository.findPriceByProductId(productId);
    }

    public Page<ProductListResponse> getPagedProductList(Pageable pageable) {
        return productRepository.findAll(pageable).map(ProductListResponse::new);
    }

    public ProductDetailResponse getProductDetail(Long productId) {
        Product product = findById(productId);
        return new ProductDetailResponse(product);
    }

    public Page<ProductListResponse> getPagedProductSearchList(Pageable pageable,
        String keyword) {
        return productRepository.findByProductNameContaining(keyword, pageable);
    }
}
