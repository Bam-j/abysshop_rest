package com.joo.abysshop.controller.admin;

import com.joo.abysshop.dto.product.request.CreateProductRequest;
import com.joo.abysshop.dto.product.request.DeleteProductRequest;
import com.joo.abysshop.service.admin.AdminProductCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin/products")
@RequiredArgsConstructor
public class AdminProductController {

    private final AdminProductCommandService adminProductCommandService;

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> createProduct(
        @RequestParam("image") MultipartFile image,
        @RequestParam("productName") String productName,
        @RequestParam("price") Long price,
        @RequestParam("description") String description
    ) {
        CreateProductRequest createProductRequest = CreateProductRequest
            .of(image, productName, price, description);
        adminProductCommandService.createProduct(createProductRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteProduct(
        @RequestBody DeleteProductRequest deleteProductRequest) {
        adminProductCommandService.deleteProduct(deleteProductRequest);
        return ResponseEntity.noContent().build();
    }
}
