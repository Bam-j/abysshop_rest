package com.joo.abysshop.service.admin;

import com.joo.abysshop.dto.admin.CreateProductRequest;
import com.joo.abysshop.dto.admin.DeleteProductRequest;
import com.joo.abysshop.repository.product.ProductImageRepository;
import com.joo.abysshop.repository.product.ProductRepository;
import com.joo.abysshop.util.enums.ResultStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminProductService {

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;


    public ResultStatus createProduct(CreateProductRequest createProductRequest) {
        /*
         *  1. products_table에서 동일한 상품명이 있는지 탐색 > 발견시: PRODUCT_ALREADY_EXIST
         *  2. 없다면 상품 create
         *  3. Image 정보도 product_images_table에 저장
         */
    }

    public void deleteProduct(DeleteProductRequest deleteProductRequest) {
        /*
         *  1. id로 일치하는 레코드 찾아서 상품 delete
         *  이때, image는 cascade로 자동 삭제됨
         */
    }
}
