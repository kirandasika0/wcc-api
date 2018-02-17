package com.wcc.order;

import com.wcc.payment.Payment;
import com.wcc.user.User;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(targetEntity = Product.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private Product product;

    public enum Status {RECEIVED, PROCESSING, PROCESSED}

    private Status orderStatus;

    @Length(max = 255)
    private String specialRequest;

    @OneToOne(targetEntity = Payment.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private Payment payment;

    @OneToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private User user;

    private Long createdAt;

    public Orders() {
        this.createdAt = System.currentTimeMillis();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Status getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Status orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getSpecialRequest() {
        return specialRequest;
    }

    public void setSpecialRequest(String specialRequest) {
        this.specialRequest = specialRequest;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", product=" + product +
                ", orderStatus=" + orderStatus +
                ", specialRequest='" + specialRequest + '\'' +
                ", payment=" + payment +
                ", user=" + user +
                ", createdAt=" + createdAt +
                '}';
    }
}
