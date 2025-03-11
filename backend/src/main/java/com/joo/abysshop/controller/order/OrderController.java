package com.joo.abysshop.controller.order;

import com.joo.abysshop.util.constants.Messages;
import com.joo.abysshop.util.constants.ViewNames;
import com.joo.abysshop.dto.order.CreateOrderRequest;
import com.joo.abysshop.dto.user.UserInfoResponse;
import com.joo.abysshop.util.enums.ResultStatus;
import com.joo.abysshop.service.cart.CartService;
import com.joo.abysshop.service.order.OrderService;
import com.joo.abysshop.service.user.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final CartService cartService;
    private final UserService userService;

    @GetMapping("/order/complete")
    public String getOrderCompletePage(HttpSession session) {
        Long cartId = (Long) session.getAttribute("cartId");

        if (cartId != null) {
            //포인트 충전 후 complete 페이지 이동시에는 cart를 비우지 않도록
            cartService.clearCart(cartId);
            session.removeAttribute("cartId");
        }

        return ViewNames.ORDER_COMPLETE_PAGE;
    }

    @PostMapping("/order/create")
    public RedirectView createOrder(@ModelAttribute CreateOrderRequest createOrderRequest,
        RedirectAttributes redirectAttributes, HttpSession session) {
        ResultStatus createOrderResult = orderService.createOrder(createOrderRequest);

        if (createOrderResult.equals(ResultStatus.INSUFFICIENT_POINTS)) {
            redirectAttributes.addFlashAttribute(Messages.FAILURE_MESSAGE, "포인트가 부족합니다.");
            return new RedirectView("/user/cart/" + createOrderRequest.getUserId());
        }

        UserInfoResponse user =  (UserInfoResponse) session.getAttribute("user");
        UserInfoResponse updatedUser = UserInfoResponse.builder()
            .userId(user.getUserId())
            .cartId(user.getCartId())
            .nickname(user.getNickname())
            .userType(user.getUserType())
            .points(userService.getPoints(user.getUserId()))
            .build();
        session.setAttribute("user", updatedUser);

        Long cartId = createOrderRequest.getUserId();
        session.setAttribute("cartId", cartId);
        return new RedirectView("/order/complete");
    }
}