package com.wcc.order;

import java.util.Collection;

public interface OrderService {
    Collection<Orders> findOrderByUsername(String query);
}
