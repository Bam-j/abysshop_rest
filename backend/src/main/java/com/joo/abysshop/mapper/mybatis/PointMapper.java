package com.joo.abysshop.mapper.mybatis;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PointMapper {

    List<PointRechargeEntity> findAllPointRecharge();

    void insertPointRecharge(CreatePointRechargeEntity createPointRechargeEntity);

    void changePointRechargeState(Map<String, Object> changeStateMap);

    List<PointRechargeDetailEntity> findAllPointRechargeDetail();

    void updatePointRechargeDetail(UpdatePointRechargeDetailEntity updatePointRechargeDetailEntity);

    int countAllPointRecharges();

    List<PointRechargeEntity> findPagedPointRecharges(@Param("pageSize") int pageSize,
        @Param("offset") int offset);

    int countAllPointRechargeDetails();

    List<PointRechargeDetailEntity> findPagedPointRechargeDetails(@Param("pageSize") int pageSize,
        @Param("offset") int offset);

    int countUserPointRecharges(Long userId);

    List<PointRechargeEntity> findPagedUserPointRecharges(@Param("userId") Long userId,
        @Param("pageSize") int pageSize, @Param("offset") int offset);
}
