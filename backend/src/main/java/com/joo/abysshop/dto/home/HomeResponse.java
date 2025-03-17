package com.joo.abysshop.dto.home;

import com.joo.abysshop.dto.cart.response.CartResponse;
import com.joo.abysshop.dto.product.response.ProductListResponse;
import com.joo.abysshop.dto.user.response.UserInfoResponse;
import org.springframework.data.domain.Page;

public record HomeResponse(
    UserInfoResponse user,
    Page<ProductListResponse> productList,
    CartResponse cart
) {

    public static HomeResponse of(
        UserInfoResponse user,
        Page<ProductListResponse> productList,
        CartResponse cart) {
        return new HomeResponse(user, productList, cart);
    }
}
