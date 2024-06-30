package com.unisa.seedify.control;

import com.google.gson.*;
import com.unisa.seedify.control.utils.JsonUtils;
import com.unisa.seedify.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@WebServlet(name = "productServlet", urlPatterns = {"/product-servlet"})
public class ProductServlet extends HttpServlet implements JsonServlet {
    private boolean addProductToFavorites(HttpSession session, int productId) {
        UserBean userBean = (UserBean) session.getAttribute("user");
        if (userBean == null) {
            return false;
        }

        boolean success = false;
        try {
            EntityPrimaryKey productPrimaryKey = new EntityPrimaryKey();
            productPrimaryKey.addKey("codice_prodotto", productId);
            ProductBean productBean = productDao.doRetrive(productPrimaryKey);
            success = favoritesDao.addToFavorites(userBean, productBean);
        } catch (NumberFormatException | SQLException ignored) {
        }

        return success;
    }

    private List<ProductBean> getSessionUserFavoritesProducts(HttpSession session) {
        List<ProductBean> favoritesProducts = new ArrayList<>();

        UserBean userBean = (UserBean) session.getAttribute("user");
        if (userBean == null) {
            return favoritesProducts;
        }

        try {
            EntityPrimaryKey entityPrimaryKey = new EntityPrimaryKey();
            entityPrimaryKey.addKey("email", userBean.getEmail());
            FavoritesBean favoritesBean = favoritesDao.doRetrive(entityPrimaryKey);
            favoritesProducts = new ArrayList<>(favoritesBean.getProducts());
        } catch (SQLException ignored) {
        }

        return favoritesProducts;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        ArrayList<String> fields = new ArrayList<>(Arrays.asList(request.getParameter("fields").split(",")));
        String filter = request.getParameter("filter");

        List<ProductBean> products = null;
        switch (action) {
            case "get_all_products": {
                products = productDao.getAllActiveProducts();
                break;
            }
            case "get_latest_products" : {
                products = productDao.getAllActiveLatestProducts(10);
                break;
            }
            default: {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
            }
        }

        JsonArray rawData = this.gson.toJsonTree(products).getAsJsonArray();
        List<ProductBean> favoritesProducts = this.getSessionUserFavoritesProducts(request.getSession());
        for (JsonElement record : rawData) {
            JsonObject jsonProduct = record.getAsJsonObject();
            boolean isFavorite = false;

            int productId = jsonProduct.get("id_prodotto").getAsInt();
            for (ProductBean favoriteProduct : favoritesProducts) {
                if (favoriteProduct.getProductId() == productId) {
                    isFavorite = true;
                    break;
                }
            }
            jsonProduct.addProperty("preferito", isFavorite);
        }

        JsonElement filteredData = null;
        if (filter != null && !filter.isEmpty()) {
            filteredData = JsonUtils.filterJsonArray(rawData, fields, filter);
        } else {
            filteredData = JsonUtils.filterJsonArray(rawData, fields);
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.print(filteredData);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        String requestBody = stringBuilder.toString();
        JsonObject jsonObject = JsonParser.parseString(requestBody).getAsJsonObject();

        String action = null;
        try {
            action = jsonObject.get("action").getAsString();
        } catch (NullPointerException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing param 'action' in request body");
            return;
        }

        boolean success = false;
        switch (action) {
            case "add_to_favorites": {
                int productId;
                try {
                    productId = jsonObject.get("product_id").getAsInt();
                    success = this.addProductToFavorites(request.getSession(), productId);
                } catch (NullPointerException | JsonSyntaxException e) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing param 'product_id' in request body");
                }
                break;
            }
            default: {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
            }
        }

        if (success) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "An error occurred while processing the request");
        }
    }
}
