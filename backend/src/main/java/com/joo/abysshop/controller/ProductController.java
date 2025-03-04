package com.joo.abysshop.controller;

import com.joo.abysshop.dto.cart.CartResponse;
import com.joo.abysshop.dto.user.UserInfoResponse;
import com.joo.abysshop.service.cart.CartService;
import com.joo.abysshop.util.constants.ModelAttributeNames;
import com.joo.abysshop.util.constants.ViewNames;
import com.joo.abysshop.service.product.ProductService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final CartService cartService;

    @GetMapping("/product/detail/{productId}")
    public String getProductDetail(@PathVariable Long productId, HttpSession session, Model model) {
        UserInfoResponse user = (UserInfoResponse) session.getAttribute("user");
        if (user != null) {
            CartResponse cart = cartService.getCart(user.getUserId());
            model.addAttribute(ModelAttributeNames.CART, cart);
        }

        model.addAttribute(ModelAttributeNames.PRODUCT, productService.findById(productId));
        return ViewNames.PRODUCT_DETAIL_PAGE;
    }
}