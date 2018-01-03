package com.wcc.order;

import com.wcc.payment.Payment;
import com.wcc.payment.PaymentRepository;
import com.wcc.user.User;
import com.wcc.user.UserRepository;
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

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public Orders createNewOrder(@RequestBody Orders order) throws Exception{
        try {
            User orderUser = userRepository.findOne(order.getUser().getUserId());
            if (orderUser == null)
                throw new Exception("error while creating order.");
            order.setUser(orderUser);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        orderRepository.save(order);
        return order;
    }

    @RequestMapping(value = "/{orderId}", method = RequestMethod.GET)
    public Orders getOrderById(@PathVariable Long orderId) throws Exception{
        Orders currOrder = orderRepository.findOne(orderId);
        if (currOrder == null)
            throw new Exception("order not found.");
        return currOrder;
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

        //updating fields in the order request
        orderRepository.save(currOrder);
        return currOrder;
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