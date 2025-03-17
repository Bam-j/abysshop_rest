package com.joo.abysshop.controller.home;

import com.joo.abysshop.dto.home.HomeResponse;
import com.joo.abysshop.service.home.HomeQueryService;
import com.joo.abysshop.dto.cart.response.CartResponse;
import com.joo.abysshop.dto.product.response.ProductListResponse;
import com.joo.abysshop.dto.user.response.UserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class HomeController {

    private final HomeQueryService homeQueryService;

    @GetMapping("/home")
    public ResponseEntity<HomeResponse> getHomePageData(Pageable pageable,
        @AuthenticationPrincipal UserInfoResponse user) {
        Page<ProductListResponse> pagedProductList = homeQueryService.getPagedProductList(pageable);

        if (user != null) {
            CartResponse cart = homeQueryService.getCart(user.userId());
            HomeResponse response = new HomeResponse(user, pagedProductList, cart);
            return ResponseEntity.ok(response);
        }

        HomeResponse response = new HomeResponse(null, pagedProductList, null);
        return ResponseEntity.ok(response);
    }
}
