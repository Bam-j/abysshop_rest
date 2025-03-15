package com.joo.abysshop.controller.admin;

import com.joo.abysshop.dto.order.AdminOrderListResponse;
import com.joo.abysshop.dto.order.OrdersResponse;
import com.joo.abysshop.dto.point.AdminPointRechargeDetailListResponse;
import com.joo.abysshop.dto.point.AdminPointRechargeListResponse;
import com.joo.abysshop.dto.point.PointRechargeDetailsResponse;
import com.joo.abysshop.dto.point.PointRechargesResponse;
import com.joo.abysshop.dto.product.AdminProductListResponse;
import com.joo.abysshop.dto.product.AdminProductsResponse;
import com.joo.abysshop.service.admin.AdminDashboardQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/dashboard")
@RequiredArgsConstructor
public class AdminDashboardController {

    private final AdminDashboardQueryService adminDashboardQueryService;

    @GetMapping("/orders")
    public ResponseEntity<OrdersResponse> getOrders(Pageable pageable) {
        Page<AdminOrderListResponse> pagedOrderList = adminDashboardQueryService.getPagedOrderList(
            pageable);
        OrdersResponse response = OrdersResponse.of(pagedOrderList);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/point-recharges")
    public ResponseEntity<Object> getPointRecharges(Pageable pageable) {
        Page<AdminPointRechargeListResponse> pagedPointRechargeList = adminDashboardQueryService.getPagedPointRechargeList(
            pageable);
        PointRechargesResponse response = PointRechargesResponse.of(pagedPointRechargeList);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/point-recharge/details")
    public ResponseEntity<Object> getPointRechargeDetails(Pageable pageable) {
        Page<AdminPointRechargeDetailListResponse> pagedPointRechargeDetailList = adminDashboardQueryService.getPagedPointRechargeDetailList(
            pageable);
        PointRechargeDetailsResponse response = PointRechargeDetailsResponse.of(
            pagedPointRechargeDetailList);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/products")
    public ResponseEntity<Object> getProducts(Pageable pageable) {
        Page<AdminProductListResponse> pagedProductList = adminDashboardQueryService.getPagedProductList(
            pageable);
        AdminProductsResponse response = AdminProductsResponse.of(pagedProductList);
        return ResponseEntity.ok(response);
    }
}
