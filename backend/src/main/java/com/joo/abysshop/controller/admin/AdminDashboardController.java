package com.joo.abysshop.controller.admin;

import com.joo.abysshop.dto.order.OrderListResponse;
import com.joo.abysshop.dto.order.OrdersResponse;
import com.joo.abysshop.dto.point.PointRechargeDetailListResponse;
import com.joo.abysshop.dto.point.PointRechargeListResponse;
import com.joo.abysshop.service.admin.AdminDashboardService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/dashboard")
@RequiredArgsConstructor
public class AdminDashboardController {

    private static final int PAGE_SIZE = 10;

    private final AdminDashboardService adminDashboardService;

    @GetMapping("/orders")
    public ResponseEntity<OrdersResponse> getOrders(Pageable pageable) {
        Page<OrderListResponse> pagedOrderList = adminDashboardService.getPagedOrderList(pageable);
        OrdersResponse response = OrdersResponse.of(pagedOrderList);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/point-recharges")
    public ResponseEntity<Object> getPointRecharges(
        @RequestParam(defaultValue = "1") int page) {
        int totalPointRecharges = adminDashboardService.countPointRecharges();
        int totalPages = (int) Math.ceil((double) totalPointRecharges / PAGE_SIZE);
        List<PointRechargeListResponse> pagedPointRechargeList = adminDashboardService.getPagedPointRecharges(
            page, PAGE_SIZE);

        PointRechargesReponse response = new PointRechargesResponse(pagedPointRechargeList, page,
            totalPages);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/point-recharge/details")
    public ResponseEntity<Object> getPointRechargeDetails(
        @RequestParam(defaultValue = "1") int page) {
        int totalPointRechargeDetails = adminDashboardService.countPointRechargeDetails();
        int totalPages = (int) Math.ceil((double) totalPointRechargeDetails / PAGE_SIZE);
        List<PointRechargeDetailListResponse> pagedPointRechargeDetailList = adminDashboardService.getPagedPointRechargeDetails(
            page, PAGE_SIZE);

        PointRechargeDetailResponse response = new PointRechargeDetailResponse(
            pagedPointRechargeDetailList, page, totalPages);
        return ResponseEntity.ok(response);
    }
}
