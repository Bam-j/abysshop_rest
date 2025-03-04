package com.joo.abysshop.service.admin;

import com.joo.abysshop.dto.admin.AddProductRequest;
import com.joo.abysshop.dto.product.ProductListResponse;
import com.joo.abysshop.entity.admin.AddProductEntity;
import com.joo.abysshop.entity.admin.AddProductImageEntity;
import com.joo.abysshop.entity.product.ProductEntity;
import com.joo.abysshop.mapper.dto.ToProductDTOMapper;
import com.joo.abysshop.mapper.entity.ToProductEntityMapper;
import com.joo.abysshop.mapper.mybatis.AdminMapper;
import com.joo.abysshop.mapper.mybatis.ProductMapper;
import com.joo.abysshop.service.user.UserService;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class AdminMyPageService {

    private final AdminMapper adminMapper;
    private final ProductMapper productMapper;
    private final ToProductDTOMapper toProductDTOMapper;
    private final ToProductEntityMapper toProductEntityMapper;
    private final UserService userService;
    private final SqlSession sqlSession;

    public void addProduct(AddProductRequest addProductRequest) throws IOException {
        MultipartFile imageFile = addProductRequest.getImage();
        String originalFileName = imageFile.getOriginalFilename();
        String savePath = "C:/Users/juhyu/abysshop_img/" + originalFileName;
        imageFile.transferTo(new File(savePath));

        AddProductEntity addProductEntity = toProductEntityMapper.toAddProductEntity(
            addProductRequest);
        adminMapper.insertProduct(addProductEntity);
        Long productId = addProductEntity.getProductId();

        AddProductImageEntity addProductImageEntity = toProductEntityMapper.toAddProductImageEntity(
            originalFileName, productId);
        adminMapper.insertProductImage(addProductImageEntity);
    }

    public void removeProduct(Long productId) {
        adminMapper.deleteByProductId(productId);
    }

    public List<ProductListResponse> getPagedProducts(int page, int pageSize) {
        int offset = (page - 1) * pageSize;

        List<ProductEntity> productEntityList = productMapper.findPagedProducts(pageSize, offset);
        List<ProductListResponse> productList = new ArrayList<>();

        for (ProductEntity productEntity : productEntityList) {
            productList.add(toProductDTOMapper.toProductListResponse(productEntity));
        }

        return productList;
    }
}