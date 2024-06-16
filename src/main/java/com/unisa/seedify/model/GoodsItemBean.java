package com.unisa.seedify.model;

import java.io.Serializable;

public class GoodsItemBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private ProductBean product;
    private int quantity;

    public GoodsItemBean() {
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
}
