package com.joo.abysshop.controller.product;

import com.joo.abysshop.dto.product.response.ProductImageResourceResponse;
import com.joo.abysshop.service.product.ProductImageQueryService;
//import io.github.cdimascio.dotenv.Dotenv;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products/images")
public class ProductImageController {

    private final String imageDir;
    private final ProductImageQueryService productImageQueryService;

    public ProductImageController(
        @Value("${image.dir}") String imageDir,
        ProductImageQueryService productImageQueryService
    ) {
        this.imageDir = imageDir;
        this.productImageQueryService = productImageQueryService;
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<Resource> getProductImage(@PathVariable("fileName") String fileName)
        throws MalformedURLException, FileNotFoundException {
        ProductImageResourceResponse productImageResource = productImageQueryService.getProductImageResource(
            fileName, imageDir);
        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(productImageResource.contentType()))
            .body(productImageResource.resource());
    }
}
