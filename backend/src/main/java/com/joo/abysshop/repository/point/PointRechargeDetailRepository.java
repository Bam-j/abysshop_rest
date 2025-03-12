package com.joo.abysshop.repository.point;

import com.joo.abysshop.entity.point.PointRechargeDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointRechargeDetailRepository extends JpaRepository<PointRechargeDetail, Long> {

}
