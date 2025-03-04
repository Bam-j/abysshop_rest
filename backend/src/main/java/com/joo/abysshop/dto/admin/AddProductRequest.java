package com.joo.abysshop.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
@AllArgsConstructor
public class AddProductRequest {

    private MultipartFile image;
    //private String originalFileName;

    private String productName;
    private Long price;
    private String description;
}
