package com.joo.abysshop.repository.point;

import com.joo.abysshop.entity.point.PointRecharge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointRechargeRepository extends JpaRepository<PointRecharge, Long> {

    Page<PointRecharge> findByUserId(Long userId, Pageable pageable);
}
