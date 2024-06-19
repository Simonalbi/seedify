package com.unisa.seedify.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GoodsBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private OrderBean order;
    private List<GoodsItemBean> goods;

    public GoodsBean() {
        this.goods = new ArrayList<>();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoodsBean goodsBean = (GoodsBean) o;
        return Objects.equals(order, goodsBean.order) && Objects.equals(goods, goodsBean.goods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, goods);
    }
}
