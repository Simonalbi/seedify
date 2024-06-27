package com.unisa.seedify.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FavoritesBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @SerializedName("utente")
    private UserBean user;

    @SerializedName("prodotti")
    private List<ProductBean> products;

    public FavoritesBean() {
        this.products = new ArrayList<>();
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public List<ProductBean> getProducts() {
        return products;
    }

    public void setProducts(List<ProductBean> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavoritesBean that = (FavoritesBean) o;
        return Objects.equals(user, that.user) && Objects.equals(products, that.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, products);
    }
}
