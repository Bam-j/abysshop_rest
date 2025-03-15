package com.joo.abysshop.controller.admin;

import com.joo.abysshop.dto.admin.response.AdminOrderListResponse;
import com.joo.abysshop.dto.admin.response.AdminOrdersResponse;
import com.joo.abysshop.dto.admin.response.AdminPointRechargeDetailListResponse;
import com.joo.abysshop.dto.admin.response.AdminPointRechargeListResponse;
import com.joo.abysshop.dto.admin.response.AdminPointRechargeDetailsResponse;
import com.joo.abysshop.dto.admin.response.AdminPointRechargesResponse;
import com.joo.abysshop.dto.admin.response.AdminProductListResponse;
import com.joo.abysshop.dto.admin.response.AdminProductsResponse;
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
    public ResponseEntity<AdminOrdersResponse> getOrders(Pageable pageable) {
        Page<AdminOrderListResponse> pagedOrderList = adminDashboardQueryService.getPagedOrderList(
            pageable);
        AdminOrdersResponse response = AdminOrdersResponse.of(pagedOrderList);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/point-recharges")
    public ResponseEntity<AdminPointRechargesResponse> getPointRecharges(Pageable pageable) {
        Page<AdminPointRechargeListResponse> pagedPointRechargeList = adminDashboardQueryService.getPagedPointRechargeList(
            pageable);
        AdminPointRechargesResponse response = AdminPointRechargesResponse.of(
            pagedPointRechargeList);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/point-recharge/details")
    public ResponseEntity<AdminPointRechargeDetailsResponse> getPointRechargeDetails(
        Pageable pageable) {
        Page<AdminPointRechargeDetailListResponse> pagedPointRechargeDetailList = adminDashboardQueryService.getPagedPointRechargeDetailList(
            pageable);
        AdminPointRechargeDetailsResponse response = AdminPointRechargeDetailsResponse.of(
            pagedPointRechargeDetailList);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/products")
    public ResponseEntity<AdminProductsResponse> getProducts(Pageable pageable) {
        Page<AdminProductListResponse> pagedProductList = adminDashboardQueryService.getPagedProductList(
            pageable);
        AdminProductsResponse response = AdminProductsResponse.of(pagedProductList);
        return ResponseEntity.ok(response);
    }
}
