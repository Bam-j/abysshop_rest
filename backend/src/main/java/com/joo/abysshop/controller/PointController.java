package com.joo.abysshop.controller;

import com.joo.abysshop.util.constants.RedirectMappings;
import com.joo.abysshop.dto.point.CreatePointRechargeRequest;
import com.joo.abysshop.dto.point.UpdatePointRechargeDetailRequest;
import com.joo.abysshop.service.admin.AdminPointManagementService;
import com.joo.abysshop.service.point.PointRechargeManagementService;
import com.joo.abysshop.service.point.PointRechargeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
public class PointController {

    private final PointRechargeService pointRechargeService;
    private final PointRechargeManagementService pointRechargeManagementService;
    private final AdminPointManagementService adminPointManagementService;

    @PostMapping("/point/recharge/request")
    public String createPointRecharge(
        @ModelAttribute CreatePointRechargeRequest createPointRechargeRequest) {
        pointRechargeService.createPointRecharge(createPointRechargeRequest);
        return RedirectMappings.REDIRECT_ORDER_COMPLETE;
    }

    @PostMapping("/point/recharge/detail")
    public RedirectView updatePointRechargeDetail(
        @ModelAttribute UpdatePointRechargeDetailRequest updatePointRechargeDetailRequest) {
        pointRechargeManagementService.updatePointRechargeDetail(updatePointRechargeDetailRequest);
        return new RedirectView("/admin/my-page?menu=point-recharge-management");
    }
}
