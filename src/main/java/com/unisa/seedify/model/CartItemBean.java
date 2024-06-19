package com.unisa.seedify.model;

import java.io.Serializable;
import java.util.Objects;

public class CartItemBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private ProductBean product;
    private int quantity;

    public CartItemBean() {
    }

    public ProductBean getProduct() {
        return product;
    }

    public void setProduct(ProductBean product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItemBean that = (CartItemBean) o;
        return Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(product);
    }
}
