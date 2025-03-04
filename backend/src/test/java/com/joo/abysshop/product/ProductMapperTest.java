package com.joo.abysshop.product;

import com.joo.abysshop.mapper.mybatis.ProductMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductMapperTest {

    @Autowired
    private ProductMapper productMapper;

    @Test
    @DisplayName("findProductIdByProductName 테스트")
    void findProductIdByProductNameTest() {
        //given
        Long productId;

        //when
        productId = productMapper.findProductIdByProductName("상품14");

        //then
        assertEquals(14L, productId);
    }
}