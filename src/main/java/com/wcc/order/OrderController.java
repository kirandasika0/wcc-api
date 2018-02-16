package com.wcc.order;

import com.wcc.payment.Payment;
import com.wcc.payment.PaymentRepository;
import com.wcc.user.User;
import com.wcc.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.*;
import sun.misc.Request;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public Orders createNewOrder(@RequestBody Orders order) throws Exception{

        // Detect if user is in the database
        User newUser = new User();
        newUser.setDisplayName(order.getUser().getDisplayName());
        newUser.setFullName(order.getUser().getFullName());
        newUser.setMobileNumber(order.getUser().getMobileNumber());
        newUser.setEmail(order.getUser().getEmail());
        userRepository.save(newUser);


        Product orderProduct = productRepository.findOne(order.getProduct().getId());
        if (orderProduct == null) {
            throw new Exception("error while creating order.");
        }

        order.setProduct(orderProduct);
        order.setUser(newUser);

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
        currOrder.setProduct((orderIn.getProduct() == null) ?
                currOrder.getProduct() : orderIn.getProduct());

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
        return orderRepository.findNewOrdersByLimit(numOrders);
    }

    @RequestMapping(value = "/old", method = RequestMethod.GET)
    public Collection<Orders> findOldOrders() {
        return orderRepository.findRecentOrdersWithLimit(100);
    }

    @RequestMapping(value = "/{orderId}/payment", method = RequestMethod.POST)
    public Orders processPayment(@PathVariable Long orderId) {
        Orders currOrder = orderRepository.findOne(orderId);
        Payment newPayment = new Payment();
        currOrder.setPayment(newPayment);

        paymentRepository.save(newPayment);
        return currOrder;
    }

    @RequestMapping(value = "/product/new", method = RequestMethod.POST)
    public Product newProduct(@RequestBody Product productIn) {
        return productRepository.save(productIn);
    }

    @RequestMapping(value = "/product/", method = RequestMethod.GET)
    public Collection<Product> allProducts() {

        Iterator<Product> itr = productRepository.findAll().iterator();
        Collection<Product> products = new ArrayList<>();
        itr.forEachRemaining(products::add);
        return products;
    }
}