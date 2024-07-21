package com.unisa.seedify.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class OrderBean extends BaseBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @SerializedName("id_ordine")
    private int orderId;

    @SerializedName("carta_di_credito")
    private CreditCardBean creditCard;

    @SerializedName("utente")
    private UserBean user;

    @SerializedName("indirizzo")
    private AddressBean address;

    @SerializedName("data_ordine")
    private Date orderDate;

    @SerializedName("data_consegna")
    private Date deliveryDate;

    @SerializedName("prezzo_totale")
    private double totalPrice;

    public OrderBean() {
        totalPrice = 0.0f;
    }

    public OrderBean(CreditCardBean creditCard, UserBean user, AddressBean address, Date orderDate, double totalPrice) {
        this.creditCard = creditCard;
        this.user = user;
        this.address = address;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
    }

    public int getOrderId() {
        return orderId;
    }

    protected void setOrderId(int id) {
        this.orderId = id;
    }

    public AddressBean getAddress() {
        return address;
    }

    protected void setAddress(AddressBean address) {
        this.address = address;
    }

    public UserBean getUser() {
        return user;
    }

    protected void setUser(UserBean user) {
        this.user = user;
    }

    public CreditCardBean getCreditCard() {
        return creditCard;
    }

    protected void setCreditCard(CreditCardBean card) {
        this.creditCard = card;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    protected void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    protected void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    protected void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderBean orderBean = (OrderBean) o;
        return orderId == orderBean.orderId;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(orderId);
    }
}
