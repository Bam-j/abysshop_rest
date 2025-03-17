package com.joo.abysshop.controller.user;

import com.joo.abysshop.dto.point.request.CreatePointRechargeRequest;
import com.joo.abysshop.service.user.UserPointCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users/points")
@RequiredArgsConstructor
public class UserPointController {

    private final UserPointCommandService userPointCommandService;

    @PostMapping("/recharges")
    public ResponseEntity<Void> createPointRecharge(
        CreatePointRechargeRequest createPointRechargeRequest) {
        userPointCommandService.createPointRecharge(createPointRechargeRequest);
        return ResponseEntity.noContent().build();
    }
}
