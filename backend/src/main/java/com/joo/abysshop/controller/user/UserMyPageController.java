package com.joo.abysshop.controller.user;

import com.joo.abysshop.dto.order.AdminOrderListResponse;
import com.joo.abysshop.dto.point.PointRechargeListResponse;
import com.joo.abysshop.service.user.UserMyPageService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users/my-page")
@RequiredArgsConstructor
public class UserMyPageController {

    private final UserMyPageService userMyPageService;

    @GetMapping("/orders")
    public ResponseEntity<Object> getUserOrders(
        @RequestParam Long userId,
        @RequestParam(defaultValue = "1") int page) {
        int pageSize = 10;
        int totalOrders = userMyPageService.countOrdersByUser(userId);
        int totalPages = (int) Math.ceil((double) totalOrders / pageSize);
        List<AdminOrderListResponse> pagedUserOrderList = userMyPageService.getPagedUserOrders(
            userId, page, pageSize);

        UserOrdersResponse response = new UserOrdersResponse(pagedUserOrderList, page, totalPages);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/point-recharges")
    public ResponseEntity<Object> getUserPointRecharges(
        @RequestParam Long userId,
        @RequestParam(defaultValue = "1") int page) {
        int pageSize = 10;
        int totalPointRecharges = userMyPageService.countPointRechargesByUser(userId);
        int totalPages = (int) Math.ceil((double) totalPointRecharges / pageSize);
        List<PointRechargeListResponse> pagedUserPointRechargeList = userMyPageService.getPagedUserPointRecharges(
            userId, page, pageSize);

        UserPointRechargeResponse response = new UserPointRechargeResponse(
            pagedUserPointRechargeList, page, totalPages);
        return ResponseEntity.ok(response);
    }
}
