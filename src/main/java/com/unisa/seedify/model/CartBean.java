package com.unisa.seedify.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CartBean extends BaseBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @SerializedName("utente")
    private UserBean user;

    @SerializedName("prodotti_carrello")
    private List<CartItemBean> cartItems;

    public CartBean() {
        this.cartItems = new ArrayList<>();
    }

    public UserBean getUser() {
        return user;
    }

    protected void setUser(UserBean user) {
        this.user = user;
    }

    public List<CartItemBean> getCartItems() {
        return cartItems;
    }

    protected void setCartItems(List<CartItemBean> cartItems) {
        this.cartItems = cartItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartBean cartBean = (CartBean) o;
        return Objects.equals(user, cartBean.user) && Objects.equals(cartItems, cartBean.cartItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, cartItems);
    }
}
