package com.hss01248.batis.mapper;

import com.hss01248.batis.po.OrderCustom;
import com.hss01248.batis.po.Orders;
import com.hss01248.batis.po.User;

import java.util.List;

/**
 * Created by Administrator on 2017/6/4.
 */
public interface OrdersMapperCustom {

    List<OrderCustom> findOrderUserList() throws Exception;

    List<Orders> findOrderUserListMap() throws Exception;

    List<Orders> findOrderAndDetails() throws Exception;

    List<User> findUsersAndOrders() throws Exception;

    List<Orders> findOrderUserListLazyLoading() throws Exception;
}
