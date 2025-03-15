package com.joo.abysshop.dto.point;

import java.util.List;
import org.springframework.data.domain.Page;

public record PointRechargeDetailsResponse(
    List<AdminPointRechargeDetailListResponse> pointRechargeDetails,
    int currentPage,
    int totalPages,
    long totalElements
) {

    public static PointRechargeDetailsResponse of(
        Page<AdminPointRechargeDetailListResponse> pointRechargeDetailPage) {
        return new PointRechargeDetailsResponse(
            pointRechargeDetailPage.getContent(),
            pointRechargeDetailPage.getNumber(),
            pointRechargeDetailPage.getTotalPages(),
            pointRechargeDetailPage.getTotalElements()
        );
    }
}
