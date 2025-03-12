package com.joo.abysshop.service.home;

import com.joo.abysshop.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HomeService {

    private final ProductRepository productRepository;

    /*
     * Pagiantion된 모든 products 가져오기
     */
}
