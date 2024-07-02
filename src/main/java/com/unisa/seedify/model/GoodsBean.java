package com.unisa.seedify.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GoodsBean extends BaseBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @SerializedName("ordine")
    private OrderBean order;

    @SerializedName("prodotti_ordine")
    private List<GoodsItemBean> goods;

    public GoodsBean() {
        this.goods = new ArrayList<>();
    }

    public OrderBean getOrder() {
        return order;
    }

    protected void setOrder(OrderBean order) {
        this.order = order;
    }

    public List<GoodsItemBean> getGoods() {
        return goods;
    }

    protected void setGoods(List<GoodsItemBean> goods) {
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
