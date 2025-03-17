package com.joo.abysshop.service.product;

import com.joo.abysshop.dto.product.response.ProductImageResourceResponse;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductImageQueryService {

    public ProductImageResourceResponse getProductImageResource(String originalFileName,
        String imageDir) throws MalformedURLException, FileNotFoundException {
        Path path = Paths.get(imageDir + originalFileName);

        if (!Files.exists(path)) {
            throw new FileNotFoundException("이미지 파일을 찾을 수 없습니다. fileName: " + originalFileName);
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

        return new ProductImageResourceResponse(resource, contentType);
    }

    private String getFileExtension(String fileName) {
        int lastIndex = fileName.lastIndexOf(".");
        return (lastIndex == -1) ? "" : fileName.substring(lastIndex + 1);
    }
}
