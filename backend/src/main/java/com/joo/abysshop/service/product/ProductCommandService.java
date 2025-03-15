package com.joo.abysshop.service.product;

import com.joo.abysshop.dto.product.request.CreateProductRequest;
import com.joo.abysshop.dto.product.request.DeleteProductRequest;
import com.joo.abysshop.entity.product.Product;
import com.joo.abysshop.factory.ProductFactory;
import com.joo.abysshop.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductCommandService {

    private final ProductRepository productRepository;
    private final ProductQueryService productQueryService;

    @Transactional
    public void createProduct(CreateProductRequest createProductRequest) {
        Product product = ProductFactory.of(createProductRequest);
        productRepository.save(product);
    }

    @Transactional
    public void deleteProduct(DeleteProductRequest deleteProductRequest) {
        Product product = productQueryService.findById(deleteProductRequest.productId());
        productRepository.delete(product);
    }
}
