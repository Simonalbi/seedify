package com.unisa.seedify.control;

import com.google.gson.*;
import com.unisa.seedify.utils.JsonUtils;
import com.unisa.seedify.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.SQLException;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "productServlet", value = "/product-servlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 10) // 2MB, 10MB, 10MB
public class ProductServlet extends HttpServlet implements JsonServlet {
    private static final String UPLOAD_DIR_NAME = "uploads";
    private static Path UPLOAD_DIR_PATH;

    @Override
    public void init() throws ServletException {
        super.init();
        UPLOAD_DIR_PATH = Paths.get(getServletContext().getRealPath("") + UPLOAD_DIR_NAME);
        try {
            Files.createDirectories(UPLOAD_DIR_PATH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        ArrayList<String> fields = new ArrayList<>(Arrays.asList(request.getParameter("fields").split(",")));
        String filter = request.getParameter("filter");

        List<ProductBean> products = null;
        switch (action) {
            case "get_all_products": {
                String keywords = request.getParameter("keywords");
                if (keywords != null) {
                    keywords = keywords.replace("%20", " ");
                }

                products = productDao.getAllActiveProducts(keywords);
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
            case "get_related_products": {
                String category = request.getParameter("category");
                products = productDao.getAllActiveCategoryProducts(20, category);

                ProductBean watchingProduct = (ProductBean) request.getSession(true).getAttribute("watching_product");
                if (watchingProduct != null) {
                    products.remove(watchingProduct);
                }

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing param 'action' in request body");
            return;
        }

        HashMap<String, Object> parts = new HashMap<>();
        for (Part part : request.getParts()) {
            String fileName = part.getSubmittedFileName();
            if (fileName != null && !fileName.isEmpty()) {
                Path tempFilePath = Paths.get(UPLOAD_DIR_PATH.toString() + File.separator + fileName);
                part.write(tempFilePath.toString());
                parts.put("image", Files.readAllBytes(tempFilePath));
                Files.delete(tempFilePath);
            } else {
                String fieldName = part.getName();
                String fieldValue = request.getParameter(fieldName);
                parts.put(fieldName, fieldValue);
            }
        }

        try {
            String name = (String) parts.get("name");
            float price = Float.parseFloat((String) parts.get("price"));
            int quantity = Integer.parseInt((String) parts.get("quantity"));
            String plantType = (String) parts.get("plantType");
            ProductBean.RequiredWater requiredWater = ProductBean.RequiredWater.fromString((String) parts.get("required-water"));
            ProductBean.Seasons season = ProductBean.Seasons.fromString((String) parts.get("season"));
            String description = (String) parts.get("description");
            byte[] image = (byte[]) parts.get("image");

            boolean success = false;
            switch (action) {
                case "add_product": {
                    try {
                        ProductBean productBean = new ProductBean(0, name, image, price, quantity, season, requiredWater, plantType, description, new Date(System.currentTimeMillis()));
                        productDao.doSave(productBean);
                        success = true;
                    } catch (SQLException ignored) {}
                    break;
                }
                case "edit_product": {
                    try {
                        String rawProductPrimaryKey = request.getParameter("entity_primary_key");
                        EntityPrimaryKey productPrimaryKey = BaseBean.parsePrimaryKey(rawProductPrimaryKey);
                        ProductBean oldProduct = productDao.doRetrive(productPrimaryKey);

                        ProductBean newProduct = new ProductBean(oldProduct.getProductId(), name, image, price, quantity, season, requiredWater, plantType, description, new Date(System.currentTimeMillis()));
                        productDao.doUpdate(newProduct);

                        success = true;
                    } catch (SQLException ignored) {}
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
        } catch (NullPointerException | ClassCastException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing or invalid parameter in request body");
        }
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
