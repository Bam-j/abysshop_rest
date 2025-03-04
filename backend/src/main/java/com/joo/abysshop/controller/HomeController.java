package com.joo.abysshop.controller;

import com.joo.abysshop.util.constants.ModelAttributeNames;
import com.joo.abysshop.util.constants.ViewNames;
import com.joo.abysshop.dto.cart.CartResponse;
import com.joo.abysshop.dto.product.ProductListResponse;
import com.joo.abysshop.dto.user.UserInfoResponse;
import com.joo.abysshop.service.cart.CartService;
import com.joo.abysshop.service.product.ProductService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ProductService productService;
    private final CartService cartService;

    @GetMapping("/")
    public String home(@RequestParam(defaultValue = "1") int page, HttpSession session,
        Model model) {
        int pageSize = 12;
        int totalProducts = productService.getTotalProductCount();
        int totalPages = (int) Math.ceil((double) totalProducts / pageSize);

        List<ProductListResponse> pagedProductList = productService.findPagedProducts(page,
            pageSize);
        model.addAttribute(ModelAttributeNames.PRODUCT_LIST, pagedProductList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        UserInfoResponse user = (UserInfoResponse) session.getAttribute("user");
        if (user != null) {
            CartResponse cart = cartService.getCart(user.getUserId());
            model.addAttribute(ModelAttributeNames.CART, cart);
        }

        return ViewNames.INDEX_PAGE;
    }
}