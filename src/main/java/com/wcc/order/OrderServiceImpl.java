package com.wcc.order;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;

public class OrderServiceImpl implements OrderService{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Collection<Orders> findOrderByUserId(Long userIdIn) {
        Query orderByUserIdQuery = entityManager
                .createNativeQuery("SELECT * FROM wcc.orders WHERE user_id = ? ORDER BY id DESC", Orders.class);

        return orderByUserIdQuery.getResultList();
    }
}