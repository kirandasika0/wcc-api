package com.wcc.order;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class OrderServiceImpl implements OrderService{
    @PersistenceContext
    EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public Orders findOrderByUserId(Long query) {
        Query query1 = entityManager.createNativeQuery("SELECT * FROM wcc.orders WHERE orders.user_id LIMIT 1", Orders.class);
        //noinspection JpaQueryApiInspection
        query1.setParameter(1, query + "%");
        return (Orders) query1.getResultList();
    }
}