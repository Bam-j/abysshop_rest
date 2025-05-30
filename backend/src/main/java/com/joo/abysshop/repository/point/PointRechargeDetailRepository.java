package com.joo.abysshop.repository.point;

import com.joo.abysshop.entity.point.PointRechargeDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface PointRechargeDetailRepository extends JpaRepository<PointRechargeDetail, Long> {

    @Override
    @NonNull
    @EntityGraph(attributePaths = "pointRecharge")
    Page<PointRechargeDetail> findAll(@NonNull Pageable pageable);
}
