package com.wcc.order;

import com.wcc.payment.Payment;
import com.wcc.payment.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController

@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public Orders createNewOrder(@RequestBody Orders order) {
        return orderRepository.save(order);
    }

    @RequestMapping(value = "/{orderId}", method = RequestMethod.GET)
    public Orders getOrderById(@PathVariable Long orderId) {
        return orderRepository.findOne(orderId);
    }

    @RequestMapping(value = "/{orderId}", method = RequestMethod.PUT)
    public Orders updateOrderDetails(@PathVariable Long orderId, @RequestBody Orders orderIn) {
        Orders currOrder = orderRepository.findOne(orderId);
        currOrder.setItemName((orderIn.getItemName() == null) ?
                currOrder.getItemName() : orderIn.getItemName());

        currOrder.setSpecialRequest((orderIn.getSpecialRequest() == null) ?
                currOrder.getSpecialRequest() : orderIn.getSpecialRequest());

        currOrder.setOrderStatus((orderIn.getOrderStatus() == null) ?
                currOrder.getOrderStatus() : orderIn.getOrderStatus());

        currOrder.setUserId(currOrder.getUserId());

        //updating fields in the order request
        orderRepository.save(currOrder);
        return currOrder;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Collection<Orders> searchByUserId(@RequestBody Long userId) {
        return orderRepository.findOrderByUserId(userId);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Collection<Orders> findOrders(@RequestParam(name = "num", defaultValue = "10", required = false) Integer numOrders) throws Exception{
        if (numOrders < 0) {
            throw new Exception("invalid query limit number");
        }
        return orderRepository.findRecentOrdersWithLimit(numOrders);
    }

    @RequestMapping(value = "/{orderId}/payment", method = RequestMethod.POST)
    public Orders processPayment(@PathVariable Long orderId) {
        Orders currOrder = orderRepository.findOne(orderId);
        Payment newPayment = new Payment();
        currOrder.setPayment(newPayment);

        paymentRepository.save(newPayment);
        return currOrder;
    }
}