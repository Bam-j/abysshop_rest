package com.joo.abysshop.controller.admin;

import com.joo.abysshop.dto.admin.CreateProductRequest;
import com.joo.abysshop.dto.admin.DeleteProductRequest;
import com.joo.abysshop.util.constants.Messages;
import com.joo.abysshop.util.enums.ResultStatus;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/products")
@RequiredArgsConstructor
public class AdminProductController {

    private final AdminProductService adminProductService;

    @PostMapping("/create")
    public ResponseEntity<Object> createProduct(CreateProductRequest createProductRequest) {
        ResultStatus createProductResult = adminProductService.createProduct(createProductRequest);

        if (createProductResult.equals(ResultStatus.SUCCESS)) {
            return ResponseEntity.noContent().build();
        } else if (createProductResult.equals(ResultStatus.PRODUCT_ALREADY_EXISTS)) {
            //상품명이 동일한 경우, 같은 상품으로 취급한다.
            return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Map.of(Messages.FAILURE_MESSAGE, "이미 존재하는 상품입니다."));
        } else {
            return ResponseEntity.badRequest()
                .body(Map.of(Messages.FAILURE_MESSAGE, "새 상품 생성 요청에 실패하였습니다."));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteProduct(DeleteProductRequest deleteProductRequest) {
        adminProductService.deleteProduct(deleteProductRequest);
        return ResponseEntity.noContent().build();
    }
}
