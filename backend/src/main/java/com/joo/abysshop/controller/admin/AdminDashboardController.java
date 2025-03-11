package com.joo.abysshop.controller.admin;

import com.joo.abysshop.dto.order.OrderListResponse;
import com.joo.abysshop.dto.point.PointRechargeDetailListResponse;
import com.joo.abysshop.dto.point.PointRechargeListResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<Object> getOrders(@RequestParam(defaultValue = "1") int page) {
        int totalOrders = adminDashboardService.countOrders();
        int totalPages = (int) Math.ceil((double) totalOrders / PAGE_SIZE);
        List<OrderListResponse> pagedOrderList = adminDashboardService.getPagedOrders(page,
            PAGE_SIZE);

        OrdersResponse response = new OrdersResponse(pagedOrderList, page, totalPages);
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

    //관리자 페이지에서 remove를 위한 전체 상품 조회는 product 컨트롤러로 이동
}
