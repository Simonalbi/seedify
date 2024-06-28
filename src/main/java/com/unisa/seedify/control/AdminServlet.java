package com.unisa.seedify.control;

import com.google.gson.*;
import com.unisa.seedify.model.*;
import com.unisa.seedify.model.serializers.ProductBeanSerializer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.*;

@WebServlet(name="adminServlet", value="/admin-servlet")
public class AdminServlet extends HttpServlet {
    private static class TableDataResponse {
        private final boolean canEdit;
        private final boolean canDelete;
        private final List<Object> data;

        public TableDataResponse(boolean canEdit, boolean canDelete, List<Object> data) {
            this.canEdit = canEdit;
            this.canDelete = canDelete;
            this.data = data;
        }
    }

    private static final UserDao userDao = UserDao.getInstance();
    private static final OrderDao orderDao = OrderDao.getInstance();
    private static final ProductDao productDao = ProductDao.getInstance();

    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(ProductBean.class, new ProductBeanSerializer())
            .create();

    private JsonArray filterJsonArray(JsonArray jsonArray, List<String> fields) {
        JsonArray filteredData = new JsonArray();
        for (JsonElement record : jsonArray) {
            JsonObject filteredRecord = new JsonObject();
            for (String field : fields) {
                JsonObject object = record.getAsJsonObject();
                String[] path = field.split("\\.");
                for (String pathElement : path) {
                    if (pathElement.equals(path[path.length - 1])) {
                        try {
                            filteredRecord.addProperty(pathElement, object.get(pathElement).getAsString());
                        } catch (NullPointerException e) {
                            filteredRecord.addProperty(pathElement, "N/A");
                        }
                    } else {
                        object = object.getAsJsonObject(pathElement);
                    }
                }
            }
            filteredData.add(filteredRecord);
        }

        return filteredData;
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) {
        String rawProductPrimaryKey = request.getParameter("entity_primary_key");
        if (rawProductPrimaryKey == null) {
            return;
        }

        try {
            EntityPrimaryKey productPrimaryKey = BaseBean.parsePrimaryKey(rawProductPrimaryKey);
            ProductBean productBean = productDao.doRetrive(productPrimaryKey);
            productDao.doDelete(productBean);
        } catch (SQLException ignored) {
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        ArrayList<String> fields = new ArrayList<>(Arrays.asList(request.getParameter("fields").split(",")));

        boolean canEdit = false;
        boolean canDelete = false;

        ArrayList<Object> data = null;
        switch (action) {
            case "get_employees": {
                data = new ArrayList<>(userDao.getAllEmployee());
                break;
            }
            case "get_customers" : {
                data = new ArrayList<>(userDao.getAllCustomers());
                break;
            }
            case "get_orders": {
                data = new ArrayList<>(orderDao.getAllOrders());
                break;
            }
            case "get_products": {
                canEdit = true;
                canDelete = true;
                data = new ArrayList<>(productDao.getAllProducts());
                break;
            }
            default: {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
            }
        }

        TableDataResponse tableDataResponse = new TableDataResponse(canEdit, canDelete, data);
        JsonObject jsonResponseObject = this.gson.toJsonTree(tableDataResponse).getAsJsonObject();

        JsonArray rawData = jsonResponseObject.get("data").getAsJsonArray();
        JsonArray filteredData = this.filterJsonArray(rawData, fields);
        jsonResponseObject.remove("data");
        jsonResponseObject.add("data", filteredData);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.print(jsonResponseObject);
        out.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "delete_product": {
                deleteProduct(request, response);
                break;
            }
            default: {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
            }
        }
    }
}
