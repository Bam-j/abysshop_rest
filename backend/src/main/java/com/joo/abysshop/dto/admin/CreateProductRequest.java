package com.joo.abysshop.dto.admin;

import org.springframework.web.multipart.MultipartFile;

public record CreateProductRequest(
    MultipartFile image,
    String productName,
    Long price,
    String description) {

    public static CreateProductRequest of(
        MultipartFile image,
        String productName,
        Long price,
        String description) {
        return new CreateProductRequest(image, productName, price, description);
    }
}
