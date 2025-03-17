package com.joo.abysshop.dto.user.response;

import java.util.List;
import org.springframework.data.domain.Page;

public record UserPointRechargesResponse(
    List<UserPointRechargeListResponse> pointRecharges,
    int currentPage,
    int totalPages,
    long totalElements
) {

    public static UserPointRechargesResponse of(
        Page<UserPointRechargeListResponse> pointRechargePage) {
        return new UserPointRechargesResponse(
            pointRechargePage.getContent(),
            pointRechargePage.getNumber(),
            pointRechargePage.getTotalPages(),
            pointRechargePage.getTotalElements()
        );
    }
}
