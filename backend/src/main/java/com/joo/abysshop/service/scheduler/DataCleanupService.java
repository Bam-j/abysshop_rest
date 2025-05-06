package com.joo.abysshop.service.scheduler;

import com.joo.abysshop.repository.order.OrderRepository;
import com.joo.abysshop.repository.point.PointRechargeRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DataCleanupService {

    private final OrderRepository orderRepository;
    private final PointRechargeRepository pointRechargeRepository;

    @Scheduled(cron = "0 0 3 * * *")
    @Transactional
    public void cleanupDataOlderThanOneYear() {
        LocalDateTime cutoff = LocalDateTime.now().minusYears(1);
        orderRepository.deleteByOrderedAt(cutoff);
        pointRechargeRepository.deleteByRequestedAt(cutoff);
    }
}
