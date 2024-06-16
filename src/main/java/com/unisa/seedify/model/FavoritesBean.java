package com.unisa.seedify.model;

import java.io.Serializable;
import java.util.List;

public class FavoritesBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private UserBean user;
    private List<ProductBean> products;

    public FavoritesBean() {
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
}
