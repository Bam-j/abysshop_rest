package com.joo.abysshop.dto.home;

import com.joo.abysshop.dto.cart.CartResponse;
import com.joo.abysshop.dto.product.ProductListResponse;
import com.joo.abysshop.dto.user.UserInfoResponse;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class HomeResponse {
    private UserInfoResponse user;
    private List<ProductListResponse> productList;
    private int currentPage;
    private int totalPages;
    private CartResponse cart;
}
