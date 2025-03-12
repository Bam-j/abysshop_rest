package com.joo.abysshop.mapper.mybatis;

import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProductMapper {

    List<ProductEntity> findAllProducts();

    ProductEntity findById(Long id);

    Long findProductIdByProductName(String productName);

    Optional<ProductImageEntity> findProductImageEntityByProductId(Long productId);

    int countAllProducts();

    List<ProductEntity> findPagedProducts(@Param("pageSize") int pageSize, @Param("offset") int offset);

    String findOriginalFilename(Long productId);
}
