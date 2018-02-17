package com.wcc.order;

import java.util.Collection;

public interface OrderService {
    Collection<Orders> findRecentOrders();
    Collection<Orders> findNewOrdersByLimit(Integer limitIn);

}
