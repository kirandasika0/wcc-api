package com.wcc.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public Order createNewOrder(@RequestBody Order order) {
        return orderRepository.save(order);
    }

    @RequestMapping(value = "/{orderId}", method = RequestMethod.GET)
    public Order getOrderById(@PathVariable Long orderId) {
        return orderRepository.findOne(orderId);
    }

    @RequestMapping(value = "/{orderId}", method = RequestMethod.PUT)
    public void updateOrderDetails(@PathVariable Long orderId, @RequestBody Order orderIn) {
        Order currOrder = orderRepository.findOne(orderId);
    }
}
