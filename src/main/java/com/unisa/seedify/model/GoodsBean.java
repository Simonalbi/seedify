package com.unisa.seedify.model;

import java.io.Serializable;
import java.util.List;

public class GoodsBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private OrderBean order;
    private List<GoodsItemBean> goods;

    public GoodsBean() {
    }

    public OrderBean getOrder() {
        return order;
    }

    public void setOrder(OrderBean order) {
        this.order = order;
    }

    public List<GoodsItemBean> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsItemBean> goods) {
        this.goods = goods;
    }
}
