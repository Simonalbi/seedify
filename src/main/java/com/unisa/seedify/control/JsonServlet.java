package com.unisa.seedify.control;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.unisa.seedify.model.*;
import com.unisa.seedify.model.serializers.ProductBeanSerializer;
import com.unisa.seedify.model.serializers.UserBeanSerializer;
import org.apache.tika.Tika;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

public interface JsonServlet {
    AddressDao addressDao = AddressDao.getInstance();
    CartDao cartDao = CartDao.getInstance();
    CreditCardDao creditCardDao = CreditCardDao.getInstance();
    FavoritesDao favoritesDao = FavoritesDao.getInstance();
    GoodsDao goodsDao = GoodsDao.getInstance();
    LocationsDao locationsDao = LocationsDao.getInstance();
    MemorizationsDao memorizationsDao = MemorizationsDao.getInstance();
    OrderDao orderDao = OrderDao.getInstance();
    ProductDao productDao = ProductDao.getInstance();
    ReviewDao reviewDao = ReviewDao.getInstance();
    UserDao userDao = UserDao.getInstance();

    Gson gson = new GsonBuilder()
            .registerTypeAdapter(ProductBean.class, new ProductBeanSerializer())
            .registerTypeAdapter(UserBean.class, new UserBeanSerializer())
            .create();

    Tika tika = new Tika();

    static JsonObject parsePostRequestBody(HttpServletRequest request) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        String requestBody = stringBuilder.toString();
        return JsonParser.parseString(requestBody).getAsJsonObject();
    }
}
