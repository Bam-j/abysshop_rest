package com.joo.abysshop.dto.product;

public record ProductInfoRequest(String productName, Long price) {

    public static ProductInfoRequest of(String productName, Long price) {
        return new ProductInfoRequest(productName, price);
    }
}
