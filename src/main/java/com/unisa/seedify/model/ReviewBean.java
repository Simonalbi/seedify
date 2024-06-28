package com.unisa.seedify.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class ReviewBean extends BaseBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @SerializedName("utente")
    private UserBean user;

    @SerializedName("prodotto")
    private ProductBean product;

    @SerializedName("commento")
    private String comment;

    @SerializedName("voto_stelle")
    private int starRating;

    @SerializedName("data_aggiunta")
    private Date addedDate;

    public ReviewBean() {
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public ProductBean getProduct() {
        return product;
    }

    public void setProduct(ProductBean product) {
        this.product = product;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getStarRating() {
        return starRating;
    }

    public void setStarRating(int starRating) {
        this.starRating = starRating;
    }

    public Date getDateAdded() {
        return addedDate;
    }

    public void setDateAdded(Date addedDate) {
        this.addedDate = addedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReviewBean that = (ReviewBean) o;
        return Objects.equals(user, that.user) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, product);
    }
}
