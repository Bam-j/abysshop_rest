package com.joo.abysshop.controller.user;

import com.joo.abysshop.dto.user.response.UserOrderListResponse;
import com.joo.abysshop.dto.user.response.UserOrdersResponse;
import com.joo.abysshop.dto.user.response.UserPointRechargeListResponse;
import com.joo.abysshop.dto.user.response.UserPointRechargesResponse;
import com.joo.abysshop.service.user.UserMyPageQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users/my-page")
@RequiredArgsConstructor
public class UserMyPageController {

    private final UserMyPageQueryService userMyPageQueryService;

    @GetMapping("/orders")
    public ResponseEntity<UserOrdersResponse> getUserOrders(@RequestParam("userId") Long userId,
        Pageable pageable) {
        Page<UserOrderListResponse> pagedUserOrderList = userMyPageQueryService.getPagedUserOrders(
            userId, pageable);
        UserOrdersResponse response = UserOrdersResponse.of(pagedUserOrderList);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/point-recharges")
    public ResponseEntity<UserPointRechargesResponse> getUserPointRecharges(
        @RequestParam("userId") Long userId, Pageable pageable) {
        Page<UserPointRechargeListResponse> pagedUserPointRechargeList = userMyPageQueryService.getPagedUserPointRecharges(
            userId, pageable);

        UserPointRechargesResponse response = UserPointRechargesResponse.of(
            pagedUserPointRechargeList);
        return ResponseEntity.ok(response);
    }
}
