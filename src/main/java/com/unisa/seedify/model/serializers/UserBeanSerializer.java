package com.unisa.seedify.model.serializers;

import com.google.gson.*;
import com.unisa.seedify.model.OrderDao;
import com.unisa.seedify.model.UserBean;

import java.lang.reflect.Type;

public class UserBeanSerializer implements JsonSerializer<UserBean> {
    private static final OrderDao orderDao = OrderDao.getInstance();

    @Override
    public JsonElement serialize(UserBean userBean, Type typeOfSrc, JsonSerializationContext context) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.toJsonTree(userBean).getAsJsonObject();
        jsonObject.addProperty("ordini_effettuati", orderDao.getTotalOrdersByUser(userBean.getEmail()));
        return jsonObject;
    }
}
