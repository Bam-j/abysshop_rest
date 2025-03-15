package com.joo.abysshop.controller.home;

import com.joo.abysshop.dto.home.HomeResponse;
import com.joo.abysshop.service.home.HomeService;
import com.joo.abysshop.dto.cart.response.CartResponse;
import com.joo.abysshop.dto.product.response.ProductListResponse;
import com.joo.abysshop.dto.user.response.UserInfoResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class HomeController {

    private final HomeService homeService;

    @GetMapping("/home")
    public ResponseEntity<HomeResponse> getHomePageData(@RequestParam(defaultValue = "1") int page,
        @AuthenticationPrincipal UserInfoResponse user) {
        //TODO: Pageable 적용시킨 페이지네이션 구현방식 변경하기
        int pageSize = 12;
        int totalProducts = homeService.countAllProducts();
        int totalPages = (int) Math.ceil((double) totalProducts / pageSize);

        List<ProductListResponse> pagedProductList = homeService.getPagedProducts(page, pageSize);

        CartResponse cart = null;
        if (user != null) {
            cart = homeService.getUserCart(user.getUserId());
        }

        //유저, cart, 상품 목록, 페이지네이션 정보를 담아서 return
        HomeResponse response = new HomeResponse(user, pagedProductList, page, totalPages, cart);
        return ResponseEntity.ok(response);
    }
}
