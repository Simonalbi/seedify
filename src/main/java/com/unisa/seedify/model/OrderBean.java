package com.unisa.seedify.model;

import java.io.Serializable;
import java.sql.Date;

public class OrderBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private int orderId;
    private CreditCardBean creditCard;
    private UserBean user;
    private AddressBean address;
    private Date orderDate;
    private Date deliveryDate;
    private float totalPrice;

    public OrderBean() {
        totalPrice = 0.0f;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int id) {
        this.orderId = id;
    }

    public AddressBean getAddress() {
        return address;
    }

    public void setAddress(AddressBean address) {
        this.address = address;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public CreditCardBean getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCardBean card) {
        this.creditCard = card;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    //TODO: implementare un metodo per calcolare il prezzo totale
    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
}
