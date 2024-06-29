package com.unisa.seedify.control;

import com.google.gson.*;
import com.unisa.seedify.control.utils.JsonUtils;
import com.unisa.seedify.model.ProductBean;
import com.unisa.seedify.model.ProductDao;
import com.unisa.seedify.model.serializers.ProductBeanSerializer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "productServlet", urlPatterns = {"/product-servlet"})
public class ProductServlet extends HttpServlet {
    private static final ProductDao productDao = ProductDao.getInstance();

    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(ProductBean.class, new ProductBeanSerializer())
            .create();

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
}
