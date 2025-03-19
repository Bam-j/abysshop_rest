package com.joo.abysshop.repository.point;

import com.joo.abysshop.entity.point.PointRecharge;
import com.joo.abysshop.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointRechargeRepository extends JpaRepository<PointRecharge, Long> {

    Page<PointRecharge> findByUser(User user, Pageable pageable);
}
