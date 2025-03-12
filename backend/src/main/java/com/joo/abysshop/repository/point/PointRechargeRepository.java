package com.joo.abysshop.repository.point;

import com.joo.abysshop.entity.point.PointRecharge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointRechargeRepository extends JpaRepository<PointRecharge, Long> {

}
