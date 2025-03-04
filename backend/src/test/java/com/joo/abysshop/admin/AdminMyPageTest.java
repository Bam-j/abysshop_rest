package com.joo.abysshop.admin;

import com.joo.abysshop.dto.admin.AddProductRequest;
import com.joo.abysshop.dto.admin.RemoveProductRequest;
import com.joo.abysshop.dto.product.ProductDetailResponse;
import com.joo.abysshop.entity.admin.AddProductEntity;
import com.joo.abysshop.entity.product.ProductEntity;
import com.joo.abysshop.mapper.entity.ToProductEntityMapper;
import com.joo.abysshop.mapper.mybatis.ProductMapper;
import com.joo.abysshop.service.admin.AdminMyPageService;
import com.joo.abysshop.service.product.ProductService;
import java.io.IOException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class AdminMyPageTest {

    @Autowired
    private AdminMyPageService adminMyPageService;
    @Autowired
    private ToProductEntityMapper toProductEntityMapper;
    @Autowired
    private ProductMapper productMapper;

    @Test
    @Transactional
    @DisplayName("addProduct 동작 테스트 - 이미지 미첨부 상품 등록")
    void addProductTest() throws IOException {
        //given
        AddProductRequest addProductRequest = AddProductRequest.builder()
            .productName("등록 상품1")
            .price(100L)
            .description("등록 상품 설명1")
            .productType("goods")
            .build();

        //when
        AddProductEntity addProductEntity = toProductEntityMapper.toAddProductEntity(
            addProductRequest);
        System.out.println(addProductEntity.getProductType());
        adminMyPageService.addProduct(addProductRequest);

        //then
        Long productId = productMapper.findProductIdByProductName(
            addProductRequest.getProductName());
        assertThat(productId).isNotNull();
        System.out.println(productId);
    }

    @Test
    @Transactional
    @DisplayName("ProductType의 변환 및 저장 테스트")
    void ProductTypeTest() throws IOException {
        //given
        AddProductRequest addProductRequest = AddProductRequest.builder()
            .productName("등록 상품2")
            .price(100L)
            .description("등록 상품 설명2")
            .productType("goods")
            .build();

        //when
        ProductEntity productEntity = toProductEntityMapper.toProductEntity(addProductRequest);
        adminMyPageService.addProduct(addProductRequest);

        //then
        Long productId = productMapper.findProductIdByProductName(
            addProductRequest.getProductName());
        assertThat(productId).isNotNull();
    }

    @Test
    @Transactional
    @DisplayName("상품 삭제 테스트")
    void removeProductTest() {
        //given

        //when
        adminMyPageService.removeProduct(1L);
        ProductEntity result = productMapper.findById(1L);

        //then
        assertThat(result).isNull();
    }
}