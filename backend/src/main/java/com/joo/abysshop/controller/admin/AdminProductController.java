package com.joo.abysshop.controller.admin;

import com.joo.abysshop.dto.product.request.CreateProductRequest;
import com.joo.abysshop.dto.product.request.DeleteProductRequest;
import com.joo.abysshop.service.admin.AdminProductCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/products")
@RequiredArgsConstructor
public class AdminProductController {

    private final AdminProductCommandService adminProductCommandService;

    @PostMapping("/create")
    public ResponseEntity<Void> createProduct(CreateProductRequest createProductRequest) {
        adminProductCommandService.createProduct(createProductRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteProduct(DeleteProductRequest deleteProductRequest) {
        adminProductCommandService.deleteProduct(deleteProductRequest);
        return ResponseEntity.noContent().build();
    }
}
