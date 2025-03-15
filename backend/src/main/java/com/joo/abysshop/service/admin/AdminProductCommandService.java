package com.joo.abysshop.service.admin;

import com.joo.abysshop.dto.product.request.CreateProductRequest;
import com.joo.abysshop.dto.product.request.DeleteProductRequest;
import com.joo.abysshop.service.product.ProductCommandService;
import com.joo.abysshop.service.product.ProductQueryService;
import com.joo.abysshop.util.exception.ProductAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminProductCommandService {

    private final ProductQueryService productQueryService;
    private final ProductCommandService productCommandService;

    public void createProduct(CreateProductRequest createProductRequest) {
        if (productQueryService.existsByProductName(createProductRequest.productName())) {
            throw new ProductAlreadyExistsException("이미 존재하는 상품입니다.");
        }
        //TODO: 이미지 저장과 불러오기 등의 이미지 관련 로직은 후에 추가
        productCommandService.createProduct(createProductRequest);
    }

    public void deleteProduct(DeleteProductRequest deleteProductRequest) {
        productCommandService.deleteProduct(deleteProductRequest);
    }
}
