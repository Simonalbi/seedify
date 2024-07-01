package com.unisa.seedify.control;

import com.google.gson.*;
import com.unisa.seedify.utils.JsonUtils;
import com.unisa.seedify.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "productServlet", value = "/product-servlet")
public class ProductServlet extends HttpServlet implements JsonServlet {
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
            case "get_most_purchased_products": {
                products = productDao.getAllActiveMostPurchasedProducts(10);
                break;
            }
            case "get_product": {
                try {
                    EntityPrimaryKey productPrimaryKey = BaseBean.parsePrimaryKey(request.getParameter("entity_primary_key"));
                    ProductBean productBean = productDao.doRetrive(productPrimaryKey);
                    products = new ArrayList<>();
                    products.add(productBean);
                } catch (SQLException e) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid or missing param 'entity_primary_key'");
                    return;
                }
                break;
            }
            default: {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
            }
        }

        UserBean userBean = (UserBean) request.getSession(true).getAttribute("user");
        JsonArray rawData = this.gson.toJsonTree(products).getAsJsonArray();

        List<ProductBean> favoritesProducts = new ArrayList<>();
        if (userBean != null) {
            favoritesProducts = favoritesDao.getUserFavorites(userBean).getProducts();
        }

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
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        UserBean userBean = (UserBean) session.getAttribute("user");

        String action = request.getParameter("action");
        String rawProductPrimaryKey = request.getParameter("entity_primary_key");
        if (rawProductPrimaryKey == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
            return;
        }

        if (userBean.getRole().equals(UserBean.Roles.ADMIN)) {
            switch (action) {
                case "delete_product": {
                    try {
                        EntityPrimaryKey productPrimaryKey = BaseBean.parsePrimaryKey(rawProductPrimaryKey);
                        ProductBean productBean = productDao.doRetrive(productPrimaryKey);
                        productDao.doDelete(productBean);
                    } catch (SQLException ignored) {}
                    break;
                }
                default: {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
                }
            }
        }
    }
}
