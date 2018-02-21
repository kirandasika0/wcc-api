package com.wcc.order;

import java.util.Collection;

public interface OrderService {
    /*
    * Finds all recent orders that are have a status of RECEIVED
    @return Collection<Orders>
     */
    Collection<Orders> findRecentOrders();

    /*
    * Finds all new orders with a given limit.
    @param limitIn an Integer to limit the query
    @return Collection<Orders>
     */
    Collection<Orders> findNewOrdersByLimit(Integer limitIn);

}
