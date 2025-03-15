package com.joo.abysshop.dto.admin.response;

import java.util.List;
import org.springframework.data.domain.Page;

public record AdminPointRechargeDetailsResponse(
    List<AdminPointRechargeDetailListResponse> pointRechargeDetails,
    int currentPage,
    int totalPages,
    long totalElements
) {

    public static AdminPointRechargeDetailsResponse of(
        Page<AdminPointRechargeDetailListResponse> pointRechargeDetailPage) {
        return new AdminPointRechargeDetailsResponse(
            pointRechargeDetailPage.getContent(),
            pointRechargeDetailPage.getNumber(),
            pointRechargeDetailPage.getTotalPages(),
            pointRechargeDetailPage.getTotalElements()
        );
    }
}
