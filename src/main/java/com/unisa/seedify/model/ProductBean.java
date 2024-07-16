package com.unisa.seedify.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
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

    public ProductBean(int productId, String name, byte[] image, float price, int quantity, Seasons season, RequiredWater requiredWater, String plantType, String description, Date addedDate) {
        this.productId = productId;
        this.name = name;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
        this.season = season;
        this.requiredWater = requiredWater;
        this.plantType = plantType;
        this.description = description;
        this.addedDate = addedDate;
        this.state = States.ACTIVE;
    }

    public int getProductId() {
        return productId;
    }

    protected void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public byte[] getImage() {
        return image;
    }

    protected void setImage(byte[] image) {
        this.image = image;
    }

    public float getPrice() {
        return price;
    }

    protected void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    protected void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public RequiredWater getRequiredWater() {
        return requiredWater;
    }

    protected void setRequiredWater(RequiredWater requiredWater) {
        this.requiredWater = requiredWater;
    }

    public Seasons getSeason() {
        return season;
    }

    protected void setSeason(Seasons season) {
        this.season = season;
    }

    public String getPlantType() {
        return plantType;
    }

    protected void setPlantType(String plantType) {
        this.plantType = plantType;
    }

    public String getDescription() {
        return description;
    }

    protected void setDescription(String description) {
        this.description = description;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    protected void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public States getState() {
        return state;
    }

    protected void setState(States state) {
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

