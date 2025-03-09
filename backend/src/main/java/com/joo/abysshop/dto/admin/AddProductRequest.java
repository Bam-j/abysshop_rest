package com.joo.abysshop.dto.admin;

import org.springframework.web.multipart.MultipartFile;

public record AddProductRequest(
    MultipartFile image,
    String productName,
    Long price,
    String description) {

    public static AddProductRequest of(
        MultipartFile image,
        String productName,
        Long price,
        String description) {
        return new AddProductRequest(image, productName, price, description);
    }
}
