package com.wcc.order;

import com.wcc.user.User;

import java.util.Collection;

public interface OrderService {
    Collection<Orders> findOrdersByUser(User userIn);
}
