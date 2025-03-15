package com.joo.abysshop.dto.product.response;

import org.springframework.core.io.Resource;

public record ProductImageResourceResponse(Resource resource, String contentType) {

    public static ProductImageResourceResponse of(Resource resource, String contentType) {
        return new ProductImageResourceResponse(resource, contentType);
    }
}

