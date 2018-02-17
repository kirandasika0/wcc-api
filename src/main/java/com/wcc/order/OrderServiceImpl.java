package com.wcc.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Collection<Orders> findRecentOrders() {
        Stream<Orders> orderStream = StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(orderRepository.findAll().iterator(), Spliterator.ORDERED)
        ,false);
        Collection<Orders> recentOrders = new ArrayList<>();

        orderStream
                .filter(o -> o.getOrderStatus() == Orders.Status.RECEIVED ||
                        o.getOrderStatus() == Orders.Status.PROCESSING)
                .forEach(o -> recentOrders.add(o));
        return recentOrders;
    }

    @Override
    public Collection<Orders> findNewOrdersByLimit(Integer limitIn) {
        Stream<Orders> orderStream = StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(orderRepository.findAll().iterator(), Spliterator.ORDERED)
        ,false);
        Collection<Orders> newOrders = new ArrayList<>();

        orderStream
                .filter(o -> o.getOrderStatus() == Orders.Status.RECEIVED)
                .forEach(o -> newOrders.add(o));
        return newOrders;
    }
}
