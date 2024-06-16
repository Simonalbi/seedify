package com.unisa.seedify.model;

import java.io.Serializable;
import java.sql.Date;

public class CreditCardBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private String cardNumber;
    private String cvv;
    private Date expirationDate;
    private String name;
    private String surname;

    public CreditCardBean() {
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expirationDate = expiryDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
