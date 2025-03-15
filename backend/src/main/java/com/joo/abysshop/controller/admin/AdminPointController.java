package com.joo.abysshop.controller.admin;

import com.joo.abysshop.dto.admin.ProvidePointRequest;
import com.joo.abysshop.service.admin.AdminPointCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/points")
@RequiredArgsConstructor
public class AdminPointController {

    private final AdminPointCommandService adminPointCommandService;

    @PatchMapping("/provide")
    public ResponseEntity<Void> providePoints(ProvidePointRequest providePointRequest) {
        adminPointCommandService.providePoints(providePointRequest);
        return ResponseEntity.noContent().build();
    }
}
