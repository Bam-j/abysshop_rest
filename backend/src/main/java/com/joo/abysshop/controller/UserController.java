package com.joo.abysshop.controller;

import com.joo.abysshop.annotation.CurrentUserOnly;
import com.joo.abysshop.util.constants.ModelAttributeNames;
import com.joo.abysshop.util.constants.ViewNames;
import com.joo.abysshop.dto.cart.CartResponse;
import com.joo.abysshop.dto.order.OrderListResponse;
import com.joo.abysshop.dto.point.PointRechargeListResponse;
import com.joo.abysshop.service.cart.CartService;
import com.joo.abysshop.service.user.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final CartService cartService;

    @CurrentUserOnly
    @GetMapping("/user/my-page/{userId}")
    public String getUserMyPage(@PathVariable("userId") Long userId,
        @RequestParam("menu") String menu,
        @RequestParam(defaultValue = "1") int page,
        Model model) {
        CartResponse cart = cartService.getCart(userId);
        model.addAttribute(ModelAttributeNames.CART, cart);

        int pageSize = 10;

        if ("order-management".equals(menu)) {
            int totalOrders = userService.getUserTotalOrderCount(userId);
            int totalPages = (int) Math.ceil((double) totalOrders / pageSize);

            List<OrderListResponse> userOrderList =
                userService.getPagedOrderList(userId, page, pageSize);
            model.addAttribute(ModelAttributeNames.USER_ORDER_LIST, userOrderList);
            model.addAttribute(ModelAttributeNames.CURRENT_PAGE, page);
            model.addAttribute(ModelAttributeNames.TOTAL_PAGES, totalPages);
        } else if ("user-info".equals(menu)) {
        } else if ("point-request".equals(menu)) {
            int totalPointRecharges = userService.getUserTotalPointRechargeCount(userId);
            int totalPages = (int) Math.ceil((double) totalPointRecharges / pageSize);

            List<PointRechargeListResponse> userPointRechargeList =
                userService.getPagedPointRechargeList(userId, page, pageSize);
            model.addAttribute(ModelAttributeNames.USER_POINT_RECHARGE_LIST, userPointRechargeList);
            model.addAttribute(ModelAttributeNames.CURRENT_PAGE, page);
            model.addAttribute(ModelAttributeNames.TOTAL_PAGES, totalPages);
        }

        return ViewNames.USER_MY_PAGE;
    }
}