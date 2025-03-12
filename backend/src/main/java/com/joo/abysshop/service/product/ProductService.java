package com.joo.abysshop.service.product;

import com.joo.abysshop.dto.product.ProductDetailResponse;
import com.joo.abysshop.repository.product.ProductImageRepository;
import com.joo.abysshop.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;

    public ProductDetailResponse getProduct(Long productId) {
        /*
         *  1. productId로 product의 이름, 가격, 설명, product image 가져오기
         *  2. 구버전 메소드 findById 참고한 후 제거하기
         */
    }

    public ProductDetailResponse findById(Long productId) {
        ProductEntity productEntity = productMapper.findById(productId);
        String originalFileName = productMapper.findOriginalFilename(productId);
        return toProductDTOMapper.toProductDetailResponse(productEntity, originalFileName);
    }
}