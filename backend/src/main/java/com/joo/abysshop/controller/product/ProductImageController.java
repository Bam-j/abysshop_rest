package com.joo.abysshop.controller.product;

import com.joo.abysshop.dto.product.response.ProductImageResourceResponse;
import io.github.cdimascio.dotenv.Dotenv;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
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
    private final ProductImageService productImageService;

    public ProductImageController(Dotenv dotenv, ProductImageService productImageService) {
        this.imageDir = dotenv.get("IMAGE_DIR");
        this.productImageService = productImageService;
    }

    @GetMapping("/{originalFileName}")
    public ResponseEntity<Resource> getProductImage(
        @PathVariable("originalFileName") String originalFileName) throws MalformedURLException {

        //TODO: 서비스 레이어 개발할 때 line:32~59, getFileExtension() 서비스 레이어로 이동시키기
        ProductImageResourceResponse productImage = productImageService.getProductImage(
            originalFileName);
        //Resource resource = productImage.resource();
        //String contentType = productImage.contentType();

        Path path = Paths.get(imageDir + originalFileName);

        if (!Files.exists(path)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Resource resource = new UrlResource(path.toUri());

        String extension = getFileExtension(originalFileName);
        String contentType;

        if ("png".equalsIgnoreCase(extension)) {
            contentType = "image/png";
        } else if ("jpg".equalsIgnoreCase(extension) || "jpeg".equalsIgnoreCase(extension)) {
            contentType = "image/jpeg";
        } else {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(contentType))
            .body(resource);
    }

    private String getFileExtension(String fileName) {
        int lastIndex = fileName.lastIndexOf(".");
        return (lastIndex == -1) ? "" : fileName.substring(lastIndex + 1);
    }

}
