package com.joo.abysshop.dto.point;

import java.util.List;
import org.springframework.data.domain.Page;

public record PointRechargesResponse(
    List<AdminPointRechargeListResponse> pointRecharges,
    int currentPage,
    int totalPages,
    long totalElements
) {

    public static PointRechargesResponse of(
        Page<AdminPointRechargeListResponse> pointRechargePage) {
        return new PointRechargesResponse(
            pointRechargePage.getContent(),
            pointRechargePage.getNumber(),
            pointRechargePage.getTotalPages(),
            pointRechargePage.getTotalElements()
        );
    }
}
