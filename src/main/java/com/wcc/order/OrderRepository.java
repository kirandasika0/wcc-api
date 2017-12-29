package com.wcc.order;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface OrderRepository extends CrudRepository<Orders, Long>, OrderService {
    @Query(value = "SELECT * FROM wcc.orders ORDER BY id DESC LIMIT ?1", nativeQuery = true)
    Collection<Orders> findRecentOrdersWithLimit(Integer queryLimit);

    @Query(value = "SELECT * FROM wcc.orders o WHERE o.user_id = ?1 ORDER BY o.id DESC", nativeQuery = true)
    Collection<Orders> findOrdersByUserId(Integer userId);
}
