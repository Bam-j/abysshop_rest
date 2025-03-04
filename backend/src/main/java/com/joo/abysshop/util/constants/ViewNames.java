package com.joo.abysshop.util.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ViewNames {

    //home & index
    public static final String HOME_PAGE = "/";
    public static final String INDEX_PAGE = "index";

    //account
    public static final String SIGN_IN_PAGE = "account/signIn";
    public static final String SIGN_UP_PAGE = "account/signUp";

    //admin
    public static final String ADMIN_MY_PAGE = "admin/adminMyPage";

    //cart
    public static final String SHOPPING_CART_PAGE = "cart/shoppingCart";

    //order
    public static final String ORDER_COMPLETE_PAGE = "order/orderComplete";

    //product
    public static final String PRODUCT_DETAIL_PAGE = "product/productDetail";

    //user
    public static final String USER_MY_PAGE = "user/userMyPage";
}
