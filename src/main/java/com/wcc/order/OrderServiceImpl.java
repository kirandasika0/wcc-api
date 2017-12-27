package com.wcc.order;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;
import java.util.List;

public class OrderServiceImpl implements OrderService{
    @PersistenceContext
    EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public Collection<Orders> findOrderByUsername(String query) {
        Query query1 = entityManager.createNativeQuery("SELECT * FROM wcc.admin WHERE orders LIKE ?", Orders.class);
        query1.setParameter(1, query + "%");
        return query1.getResultList();
    }
}