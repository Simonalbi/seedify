package com.unisa.seedify.model;

import com.google.gson.*;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.sql.Date;
import java.util.Objects;

public class ProductBean extends BaseBean implements Serializable {
    public enum Seasons {
        @SerializedName("INVERNO")
        WINTER("INVERNO"),

        @SerializedName("PRIMAVERA")
        SPRING("PRIMAVERA"),

        @SerializedName("ESTATE")
        SUMMER("ESTATE"),

        @SerializedName("AUTUNNO")
        AUTUMN("AUTUNNO");

        private final String translation;
        Seasons(String translation) {
            this.translation = translation;
        }

        @Override
        public String toString() {
            return this.translation;
        }

        public static Seasons fromString(String translation) {
            for (Seasons season : Seasons.values()) {
                if (season.translation.equals(translation)) {
                    return season;
                }
            }
            return null;
        }
    }

    public enum RequiredWater {
        @SerializedName("POCA")
        LITTLE("POCA"),

        @SerializedName("NORMALE")
        NORMAL("NORMALE"),

        @SerializedName("MOLTA")
        A_LOT("MOLTA");

        private final String translation;
        RequiredWater(String translation) {
            this.translation = translation;
        }

        @Override
        public String toString() {
            return this.translation;
        }

        public static RequiredWater fromString(String translation) {
            for (RequiredWater requiredWater : RequiredWater.values()) {
                if (requiredWater.translation.equals(translation)) {
                    return requiredWater;
                }
            }
            return null;
        }
    }

    public enum States {
        @SerializedName("ATTIVO")
        ACTIVE("ATTIVO"),

        @SerializedName("ELIMINATO")
        DELETED("ELIMINATO");


        private final String translation;
        States(String translation) {
            this.translation = translation;
        }

        @Override
        public String toString() {
            return this.translation;
        }

        public static States fromString(String translation) {
            for (States state : States.values()) {
                if (state.translation.equals(translation)) {
                    return state;
                }
            }
            return null;
        }
    }

    private static final long serialVersionUID = 1L;

    @SerializedName("id_prodotto")
    private int productId;

    @SerializedName("nome")
    private String name;

    @SerializedName("immagine")
    private byte[] image;

    @SerializedName("prezzo")
    private float price;

    @SerializedName("quantità")
    private int quantity;

    @SerializedName("stagione")
    private Seasons season;

    @SerializedName("acqua_richiesta")
    private RequiredWater requiredWater;

    @SerializedName("tipologia")
    private String plantType;

    @SerializedName("descrizione")
    private String description;

    @SerializedName("data_aggiunta")
    private Date addedDate;

    @SerializedName("stato")
    private States state;

    public ProductBean() {
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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

    public States getState() {
        return state;
    }

    public void setState(States state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductBean that = (ProductBean) o;
        return productId == that.productId;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(productId);
    }

    @Override
    public EntityPrimaryKey getPrimaryKey() {
        EntityPrimaryKey primaryKey = new EntityPrimaryKey();
        primaryKey.addKey("codice_prodotto", this.productId);
        return primaryKey;
    }
}

