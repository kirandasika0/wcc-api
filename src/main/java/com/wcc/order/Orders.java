package com.wcc.order;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Length(max = 255)
    private String itemName;

    private enum Status {RECEIVED, PROCESSING, PROCESSED}

    private Status orderStatus;

    @Length(max = 255)
    private String specialRequest;

    private Long userId;

    //Mutators and Accessors
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + getId() + '\'' +
                ", item='" + getItemName() + '\'' +
                ", special request='" + getSpecialRequest() + '\''+
                ", order status='" + getOrderStatus() +
                "'}";
    }
}
