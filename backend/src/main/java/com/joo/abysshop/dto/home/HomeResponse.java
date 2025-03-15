package com.joo.abysshop.dto.home;

import com.joo.abysshop.dto.cart.response.CartResponse;
import com.joo.abysshop.dto.product.response.ProductListResponse;
import com.joo.abysshop.dto.user.response.UserInfoResponse;
import java.util.List;

public record HomeResponse(
    UserInfoResponse user,
    List<ProductListResponse> productList,
    int currentPage,
    int totalPages,
    CartResponse cart
) {

    public static HomeResponse of(
        UserInfoResponse user,
        List<ProductListResponse> productList,
        int currentPage,
        int totalPages,
        CartResponse cart) {
        return new HomeResponse(user, productList, currentPage, totalPages, cart);
    }
}
