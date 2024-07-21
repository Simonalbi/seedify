package com.unisa.seedify.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class CreditCardBean extends BaseBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @SerializedName("codice_carta")
    private int cardId;

    @SerializedName("numero_di_carta")
    private String cardNumber;

    @SerializedName("cvv")
    private String cvv;

    @SerializedName("data_di_scadenza")
    private Date expirationDate;

    @SerializedName("nome")
    private String name;

    @SerializedName("cognome")
    private String surname;

    public CreditCardBean() {
    }

    public CreditCardBean(String cardNumber, String cvv, Date expirationDate, String name, String surname) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expirationDate = expirationDate;
        this.name = name;
        this.surname = surname;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardCode) {
        this.cardId = cardCode;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    protected void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCvv() {
        return cvv;
    }

    protected void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    protected void setExpirationDate(Date expiryDate) {
        this.expirationDate = expiryDate;
    }

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    protected void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCardBean that = (CreditCardBean) o;
        return Objects.equals(cardNumber, that.cardNumber) && Objects.equals(cvv, that.cvv) && Objects.equals(expirationDate, that.expirationDate) && Objects.equals(name, that.name) && Objects.equals(surname, that.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, cvv, expirationDate, name, surname);
    }

    @Override
    public EntityPrimaryKey getPrimaryKey() {
        EntityPrimaryKey primaryKey = new EntityPrimaryKey();
        primaryKey.addKey("nome", this.name);
        primaryKey.addKey("cognome", this.surname);
        primaryKey.addKey("numero_carta", this.cardNumber);
        primaryKey.addKey("cvv", this.cvv);
        primaryKey.addKey("scadenza", this.expirationDate);
        return primaryKey;
    }
}
