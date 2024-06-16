package com.unisa.seedify.model;

import java.io.Serializable;
import java.util.List;

public class CartBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private UserBean user;
    private List<CartItemBean> cartItems;

    public CartBean() {
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public List<CartItemBean> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemBean> cartItems) {
        this.cartItems = cartItems;
    }
}
