package com.joo.abysshop.service.product;

import com.joo.abysshop.entity.product.Product;
import com.joo.abysshop.entity.product.ProductImage;
import com.joo.abysshop.repository.product.ProductImageRepository;
import com.joo.abysshop.repository.product.ProductRepository;
import com.joo.abysshop.util.file.FileUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ProductImageCommandService {

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;

    @Value("${IMAGE_DIR}")
    private String imageDir;

    @Transactional
    public void saveProductImage(Long productId, MultipartFile image) {
        FileUtil.save(image, imageDir, image.getOriginalFilename());

        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new EntityNotFoundException("상품이 존재하지 않습니다."));
        ProductImage productImage = new ProductImage(product, image.getOriginalFilename());

        productImageRepository.save(productImage);
    }
}
