package com.unisa.seedify.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class ReviewBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private int reviewId;
    private UserBean user;
    private ProductBean product;
    private String comment;
    private int starRating;
    private Date addedDate;

    public ReviewBean() {
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
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
        return reviewId == that.reviewId;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(reviewId);
    }
}
