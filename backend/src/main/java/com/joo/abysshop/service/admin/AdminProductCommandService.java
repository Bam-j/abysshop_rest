package com.joo.abysshop.service.admin;

import com.joo.abysshop.dto.product.request.CreateProductRequest;
import com.joo.abysshop.dto.product.request.DeleteProductRequest;
import com.joo.abysshop.service.product.ProductCommandService;
import com.joo.abysshop.service.product.ProductQueryService;
import com.joo.abysshop.util.exception.product.ProductAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminProductCommandService {

    private final ProductQueryService productQueryService;
    private final ProductCommandService productCommandService;

    @Transactional
    public void createProduct(CreateProductRequest createProductRequest) {
        if (productQueryService.existsByProductName(createProductRequest.productName())) {
            throw new ProductAlreadyExistsException("이미 존재하는 상품입니다.");
        }

        productCommandService.createProduct(createProductRequest);
    }

    @Transactional
    public void deleteProduct(DeleteProductRequest deleteProductRequest) {
        productCommandService.deleteProduct(deleteProductRequest);
    }
}
