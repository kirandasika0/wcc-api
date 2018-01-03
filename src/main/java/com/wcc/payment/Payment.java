package com.wcc.payment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long createdAt;

    private ArrayList<Long> updatedAt;

    public enum PaymentStatus {
        PROCESSING,
        PROCESSED,
        PAID
    }

    private PaymentStatus paymentStatus;

    public Payment() {
        this.createdAt = System.currentTimeMillis();
        // Default payment status
        this.paymentStatus = PaymentStatus.PROCESSING;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public ArrayList<Long> getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(ArrayList<Long> updatedAt) {
        this.updatedAt = updatedAt;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", paymentStatus=" + paymentStatus +
                '}';
    }
}
