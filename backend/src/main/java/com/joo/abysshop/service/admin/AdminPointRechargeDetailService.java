package com.joo.abysshop.service.admin;

import com.joo.abysshop.dto.point.UpdatePointRechargeDetailRequest;
import com.joo.abysshop.repository.point.PointRechargeDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminPointRechargeDetailService {

    private final PointRechargeDetailRepository pointRechargeDetailRepository;

    public void updatePointRechargeDetail(
        UpdatePointRechargeDetailRequest updatePointRechargeDetailRequest) {
        /*
         *  1. detail_id를 찾아서 계좌 정보 업데이트
         *  2. bank, account_number 암호화하여 저장
         *  3. if 만약 초회 저장시(confirmed_at == null이면) now()로 첫 입력(=입금 확인일) Date 저장하기
         *      or 입력할때마다 저장해야할까?
         */
    }
}
