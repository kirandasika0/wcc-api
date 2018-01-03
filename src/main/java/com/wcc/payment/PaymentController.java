package com.wcc.payment;

import com.wcc.order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @RequestMapping(value = "/{orderId}/update", method = RequestMethod.PUT)
    public Payment updatePaymentDetails(@PathVariable Long orderId,
                                        @RequestBody HashMap<String,String> requestPayload) throws Exception{
        Payment currPayment;
        try {
            currPayment = orderRepository.findOne(orderId).getPayment();
            if (currPayment == null) {
                throw new Exception("no payment information available");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        if (requestPayload.get("paymentStatus").toUpperCase().equals("PROCESSED")) {
            currPayment.setPaymentStatus(Payment.PaymentStatus.PROCESSED);
        }

        if (requestPayload.get("paymentStatus").toUpperCase().equals("PAID")) {
            currPayment.setPaymentStatus(Payment.PaymentStatus.PAID);
        }

        // Update payment time
        ArrayList<Long> tempUpdatedAt = currPayment.getUpdatedAt() == null ? new ArrayList<>() : currPayment.getUpdatedAt();
        tempUpdatedAt.add(System.currentTimeMillis());
        currPayment.setUpdatedAt(tempUpdatedAt);

        paymentRepository.save(currPayment);
        return currPayment;
    }
}
