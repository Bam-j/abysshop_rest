package com.joo.abysshop.dto.admin.response;

import java.util.List;
import org.springframework.data.domain.Page;

public record AdminPointRechargesResponse(
    List<AdminPointRechargeListResponse> pointRecharges,
    int currentPage,
    int totalPages,
    long totalElements
) {

    public static AdminPointRechargesResponse of(
        Page<AdminPointRechargeListResponse> pointRechargePage) {
        return new AdminPointRechargesResponse(
            pointRechargePage.getContent(),
            pointRechargePage.getNumber(),
            pointRechargePage.getTotalPages(),
            pointRechargePage.getTotalElements()
        );
    }
}
