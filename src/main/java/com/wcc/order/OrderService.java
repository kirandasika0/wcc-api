package com.wcc.order;

import java.util.Collection;

public interface OrderService {
    Collection<Order> findOrderById(Long query);
    Collection<Order> findOrderByUsername(String query);
    //Collection<Order> findOrderByTime(String query);
}
