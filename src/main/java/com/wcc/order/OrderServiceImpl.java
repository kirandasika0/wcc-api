package com.wcc.order;

import com.wcc.user.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Collection<Orders> findOrdersByUser(User userIn) {
        Stream<Orders> strm = StreamSupport.stream(Spliterators
                .spliteratorUnknownSize(orderRepository.findAll().iterator(), Spliterator.ORDERED), false);
        Collection<Orders> userOrders = new ArrayList<>();

        // Filter and get all user orders.
        strm.filter(o -> o.getUser().getUserId().equals(userIn)).forEach(o -> userOrders.add(o));

        return userOrders;
    }
}
