package com.wcc.order;

import com.wcc.user.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Collection<Orders> findOrdersByUser(User userIn) {
        Collection<Orders> userOrders = new ArrayList<>();
        Iterable<Orders> allOrders = orderRepository.findAll();
        for (Orders order: allOrders) {
            if (order.getUser().getUserId().equals(userIn.getUserId())) {
                userOrders.add(order);
            }
        }
        return userOrders;
    }
}