package com.unisa.seedify.model;

import java.io.Serializable;
import java.sql.Date;

public class ProductBean implements Serializable {
    public enum Seasons {
        WINTER("INVERNO"),
        SPRING("PRIMAVERA"),
        SUMMER("ESTATE"),
        AUTUMN("AUTUNNO");

        private final String translation;
        Seasons(String translation) {
            this.translation = translation;
        }

        @Override
        public String toString() {
            return this.translation;
        }
    }

    public enum RequiredWater {
        LITTLE("POCA"),
        NORMAL("NORMALE"),
        A_LOT("MOLTA");

        private final String translation;
        RequiredWater(String translation) {
            this.translation = translation;
        }

        @Override
        public String toString() {
            return this.translation;
        }
    }

    private static final long serialVersionUID = 1L;

    private int productId;
    private int productName;
    private String name;
    private byte[] image;
    private float price;
    private int quantity;
    private Seasons season;
    private RequiredWater requiredWater;
    private String plantType;
    private String description;
    private Date addedDate;

    public ProductBean() {
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductName() {
        return productName;
    }

    public void setProductName(int productName) {
        this.productName = productName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public RequiredWater getRequiredWater() {
        return requiredWater;
    }

    public void setRequiredWater(RequiredWater requiredWater) {
        this.requiredWater = requiredWater;
    }

    public Seasons getSeason() {
        return season;
    }

    public void setSeason(Seasons season) {
        this.season = season;
    }

    public String getPlantType() {
        return plantType;
    }

    public void setPlantType(String plantType) {
        this.plantType = plantType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }
}

