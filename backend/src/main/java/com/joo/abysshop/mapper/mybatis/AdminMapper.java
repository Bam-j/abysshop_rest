package com.joo.abysshop.mapper.mybatis;

import com.joo.abysshop.entity.admin.AddProductEntity;
import com.joo.abysshop.entity.admin.AddProductImageEntity;
import com.joo.abysshop.entity.order.OrderEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminMapper {

    List<OrderEntity> findAllOrders();

    void changeOrderState(Map<String, Object> changeStateMap);

    //parameterType으로 명시하는 방법과 @Param을 통해 타입과 이름을 지정하는 두 가지 방법이 있다.
    void providePoint(@Param("userId") Long userId, @Param("point") Long point);

    void insertProduct(AddProductEntity addProductEntity);

    void insertProductImage(AddProductImageEntity addProductImageEntity);

    void deleteByProductId(Long productId);

    void changePointRechargeState(Map<String, Object> changeStateMap);

    List<OrderEntity> findPagedOrders(@Param("pageSize") int pageSize, @Param("offset") int offset);
}
