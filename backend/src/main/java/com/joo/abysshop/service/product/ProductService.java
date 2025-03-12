package com.joo.abysshop.service.product;

import com.joo.abysshop.dto.product.ProductDetailResponse;
import com.joo.abysshop.dto.product.ProductInfoRequest;
import com.joo.abysshop.dto.product.ProductListResponse;
import com.joo.abysshop.mapper.dto.ToProductDTOMapper;
import com.joo.abysshop.mapper.mybatis.ProductMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;
    private final ToProductDTOMapper toProductDTOMapper;

    //이 메소드는 pagination 개발 이후 미사용. 추후 삭제
    public List<ProductListResponse> findAllProducts() {
        List<ProductEntity> productEntityList = productMapper.findAllProducts();
        List<ProductListResponse> productList = new ArrayList<>();

        for (ProductEntity productEntity : productEntityList) {
            Long productId = productEntity.getProductId();
            Optional<ProductImageEntity> optionalProductImageEntity = productMapper.findProductImageEntityByProductId(
                productId);

            if (optionalProductImageEntity.isPresent()) {
                ProductImageEntity productImageEntity = optionalProductImageEntity.get();
                String originalFileName = productImageEntity.getOriginalFileName();

                productList.add(
                    toProductDTOMapper.toProductListResponseWithImage(productEntity,
                        originalFileName));
            } else {
                productList.add(toProductDTOMapper.toProductListResponse(productEntity));
            }
        }

        return productList;
    }

    public ProductDetailResponse findById(Long productId) {
        ProductEntity productEntity = productMapper.findById(productId);
        String originalFileName = productMapper.findOriginalFilename(productId);
        return toProductDTOMapper.toProductDetailResponse(productEntity, originalFileName);
    }

    public ProductInfoRequest getProductInfo(Long productId) {
        ProductEntity productEntity = productMapper.findById(productId);
        return toProductDTOMapper.toProductInfoRequest(productEntity);
    }

    public int getTotalProductCount() {
        return productMapper.countAllProducts();
    }

    public List<ProductListResponse> findPagedProducts(int page, int pageSize) {
        int offset = (page - 1) * pageSize;

        List<ProductEntity> productEntityList = productMapper.findPagedProducts(pageSize, offset);
        List<ProductListResponse> productList = new ArrayList<>();

        for (ProductEntity productEntity : productEntityList) {
            Long productId = productEntity.getProductId();
            Optional<ProductImageEntity> optionalProductImageEntity = productMapper.findProductImageEntityByProductId(
                productId);

            if (optionalProductImageEntity.isPresent()) {
                ProductImageEntity productImageEntity = optionalProductImageEntity.get();
                String originalFileName = productImageEntity.getOriginalFileName();

                productList.add(
                    toProductDTOMapper.toProductListResponseWithImage(productEntity,
                        originalFileName)
                );
            } else {
                productList.add(toProductDTOMapper.toProductListResponse(productEntity));
            }
        }

        return productList;
    }
}