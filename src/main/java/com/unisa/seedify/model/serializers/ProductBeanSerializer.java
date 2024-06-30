package com.unisa.seedify.model.serializers;

import com.google.gson.*;
import com.unisa.seedify.model.ProductBean;

import java.lang.reflect.Type;

public class ProductBeanSerializer implements JsonSerializer<ProductBean> {
    @Override
    public JsonElement serialize(ProductBean productBean, Type typeOfSrc, JsonSerializationContext context) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.toJsonTree(productBean).getAsJsonObject();
        jsonObject.addProperty("immagine", "image/resource_type=product_image&entity_primary_key=" + productBean.getPrimaryKey().toString());
        jsonObject.addProperty("entity_primary_key", productBean.getPrimaryKey().toString());
        return jsonObject;
    }
}
