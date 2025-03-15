package com.joo.abysshop.service.admin;

import com.joo.abysshop.dto.order.AdminOrderListResponse;
import com.joo.abysshop.dto.point.AdminPointRechargeDetailListResponse;
import com.joo.abysshop.dto.point.AdminPointRechargeListResponse;
import com.joo.abysshop.dto.product.AdminProductListResponse;
import com.joo.abysshop.service.order.OrderQueryService;
import com.joo.abysshop.service.point.PointRechargeDetailQueryService;
import com.joo.abysshop.service.point.PointRechargeQueryService;
import com.joo.abysshop.service.product.ProductQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminDashboardQueryService {

    private final OrderQueryService orderQueryService;
    private final PointRechargeQueryService pointRechargeQueryService;
    private final PointRechargeDetailQueryService pointRechargeDetailQueryService;
    private final ProductQueryService productQueryService;

    public Page<AdminOrderListResponse> getPagedOrderList(Pageable pageable) {
        return orderQueryService.getPagedAdminOrderList(pageable);
    }

    public Page<AdminPointRechargeListResponse> getPagedPointRechargeList(Pageable pageable) {
        return pointRechargeQueryService.getPagedAdminPointRechargeList(pageable);
    }

    public Page<AdminPointRechargeDetailListResponse> getPagedPointRechargeDetailList(
        Pageable pageable) {
        return pointRechargeDetailQueryService.getPagedAdminPointRechargeDetailList(pageable);
    }

    public Page<AdminProductListResponse> getPagedProductList(Pageable pageable) {
        return productQueryService.getPagedAdminProductList(pageable);
    }
}
