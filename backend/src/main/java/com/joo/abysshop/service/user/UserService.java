package com.joo.abysshop.service.user;

import com.joo.abysshop.dto.order.OrderListResponse;
import com.joo.abysshop.dto.point.PointRechargeListResponse;
import com.joo.abysshop.dto.user.UserInfoResponse;
import com.joo.abysshop.entity.order.OrderEntity;
import com.joo.abysshop.entity.point.PointRechargeEntity;
import com.joo.abysshop.entity.user.UserEntity;
import com.joo.abysshop.mapper.dto.ToOrderDTOMapper;
import com.joo.abysshop.mapper.dto.ToPointDTOMapper;
import com.joo.abysshop.mapper.dto.ToUserDTOMapper;
import com.joo.abysshop.mapper.mybatis.CartMapper;
import com.joo.abysshop.mapper.mybatis.OrderMapper;
import com.joo.abysshop.mapper.mybatis.PointMapper;
import com.joo.abysshop.mapper.mybatis.UserMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final OrderMapper orderMapper;
    private final PointMapper pointMapper;
    private final CartMapper cartMapper;
    private final ToUserDTOMapper toUserDTOMapper;
    private final ToOrderDTOMapper toOrderDTOMapper;
    private final ToPointDTOMapper toPointDTOMapper;

    public UserInfoResponse getUserInfo(final String username) {
        Optional<UserEntity> optionalUserEntity = userMapper.findByUsername(username);

        if (optionalUserEntity.isEmpty()) {
            return null;
        }

        UserEntity userEntity = optionalUserEntity.get();
        Long userId = userEntity.getUserId();
        Long cartId = cartMapper.getCartIdByUserId(userId);
        return toUserDTOMapper.toUserInfoResponse(userEntity, cartId);
    }

    public int getUserTotalOrderCount(Long userId) {
        return orderMapper.countUserOrders(userId);
    }

    public List<OrderListResponse> getPagedOrderList(Long userId, int page, int pageSize) {
        int offset = (page - 1) * pageSize;

        List<OrderEntity> orderEntityList =
            orderMapper.findPagedUserOrders(userId, pageSize, offset);
        List<OrderListResponse> orderListResponses = new ArrayList<>();

        for (OrderEntity orderEntity : orderEntityList) {
            orderListResponses.add(toOrderDTOMapper.toOrderListResponse(orderEntity));
        }

        return orderListResponses;
    }

    public int getUserTotalPointRechargeCount(Long userId) {
        return pointMapper.countUserPointRecharges(userId);
    }

    public List<PointRechargeListResponse> getPagedPointRechargeList(Long userId, int page,
        int pageSize) {
        int offset = (page - 1) * pageSize;

        List<PointRechargeEntity> pointRechargeEntityList =
            pointMapper.findPagedUserPointRecharges(userId, pageSize, offset);
        List<PointRechargeListResponse> pointRechargeList = new ArrayList<>();

        for (PointRechargeEntity pointRechargeEntity : pointRechargeEntityList) {
            pointRechargeList.add(
                toPointDTOMapper.toPointRechargeListResponse(pointRechargeEntity));
        }

        return pointRechargeList;
    }

    public Long getPoints(final Long userId) {
        return userMapper.getUserPoints(userId);
    }
}
