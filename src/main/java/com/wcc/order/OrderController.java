package com.wcc.order;

import com.wcc.payment.Payment;
import com.wcc.payment.PaymentRepository;
import com.wcc.user.User;
import com.wcc.user.UserRepository;
import com.wcc.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.*;
import sun.misc.Request;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired UserRepository userRepository;

    // Services
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public Orders createNewOrder(@RequestBody Orders order) throws Exception{
        Optional<User> orderUser = userService.findUserByEmail(order.getUser().getEmail());

        if (!orderUser.isPresent()) {
            orderUser = Optional.of(userRepository.save(order.getUser()));
        }

        Product orderProduct = productRepository.findOne(order.getProduct().getId());
        if (orderProduct == null) {
            throw new Exception("error while creating order.");
        }

        order.setProduct(orderProduct);
        order.setUser(orderUser.get());

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
        return orderService.findNewOrdersByLimit(numOrders);
    }

    @RequestMapping(value = "/old", method = RequestMethod.GET)
    public Collection<Orders> findOldOrders() {
        return orderService.findRecentOrders();
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