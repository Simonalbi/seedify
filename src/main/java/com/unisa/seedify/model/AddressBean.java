package com.unisa.seedify.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Objects;

public class AddressBean extends BaseBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @SerializedName("id_indirizzo")
    private int addressId;

    @SerializedName("provincia")
    private String province;

    @SerializedName("città")
    private String city;

    @SerializedName("cap")
    private String cap;

    @SerializedName("via")
    private String street;

    @SerializedName("nome")
    private String name;

    @SerializedName("cognome")
    private String surname;

    @SerializedName("telefono")
    private String phone;

    @SerializedName("note")
    private String note;

    public AddressBean() {
    }

    public AddressBean(String province, String city, String cap, String street, String name, String surname, String phone, String note) {
        this.province = province;
        this.city = city;
        this.cap = cap;
        this.street = street;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.note = note;
    }

    public int getAddressId() {
        return addressId;
    }

    protected void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getProvince() {
        return province;
    }

    protected void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    protected void setCity(String city) {
        this.city = city;
    }

    public String getCap() {
        return cap;
    }

    protected void setCap(String cap) {
        this.cap = cap;
    }

    public String getStreet() {
        return street;
    }

    protected void setStreet(String street) {
        this.street = street;
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

    public String getPhone() {
        return phone;
    }

    protected void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNote() {
        return note;
    }

    protected void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressBean that = (AddressBean) o;
        return addressId == that.addressId;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(addressId);
    }
}
