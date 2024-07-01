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
import java.util.*;

@WebServlet(name = "userServlet", value = "/user-servlet")
public class UserServlet extends HttpServlet implements JsonServlet {
    private static class TableDataResponse {
        private final boolean canEdit;
        private final String editCall;
        private final boolean canDelete;
        private final String deleteCall;
        private final List<Object> data;

        public TableDataResponse(boolean canEdit, String editCall, boolean canDelete, String deleteCall, List<Object> data) {
            this.canEdit = canEdit;
            this.editCall = editCall;
            this.canDelete = canDelete;
            this.deleteCall = deleteCall;
            this.data = data;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        ArrayList<String> fields = new ArrayList<>(Arrays.asList(request.getParameter("fields").split(",")));
        fields.add("entity_primary_key");

        UserBean userBean = (UserBean) request.getSession(true).getAttribute("user");

        String dataName = "";
        boolean canEdit = false;
        String editCall = null;
        boolean canDelete = false;
        String deleteCall = null;

        ArrayList<Object> data = null;
        if (userBean.getRole().equals(UserBean.Roles.ADMIN)) {
            switch (action) {
                case "get_employees": {
                    data = new ArrayList<>(userDao.getAllEmployee());
                    dataName = "employees";
                    break;
                }
                case "get_customers" : {
                    data = new ArrayList<>(userDao.getAllCustomers());
                    dataName = "customers";
                    break;
                }
                case "get_orders": {
                    data = new ArrayList<>(orderDao.getAllOrders());
                    dataName = "all_users_orders";
                    break;
                }
                case "get_products": {
                    canEdit = true;
                    editCall = "product-servlet?action=edit_product";
                    canDelete = true;
                    deleteCall = "product-servlet?action=delete_product";

                    data = new ArrayList<>(productDao.getAllActiveProducts());
                    dataName = "all_saved_products";
                    break;
                }
                default: {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
                }
            }
        } else if (userBean.getRole().equals(UserBean.Roles.CUSTOMER)) {
            switch (action) {
                case "get_orders": {
                    data = new ArrayList<>(orderDao.getAllOrders(userBean));
                    dataName = "user_orders";
                    break;
                }
                case "get_favorites": {
                    deleteCall = "remove_from_favorites";
                    canDelete = true;

                    data = new ArrayList<>(favoritesDao.getUserFavorites(userBean).getProducts());
                    dataName = "user_favorites_products";
                    break;
                }
                case "get_credit_card": {
                    canEdit = true;
                    canDelete = true;
                    editCall = "";
                    deleteCall = "delete_credit_card";

                    data = new ArrayList<>(memorizationsDao.getAllCreditCard(userBean));
                    dataName = "user_credit_cards";
                    break;
                }
                default: {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
                }
            }
        }

        TableDataResponse tableDataResponse = new TableDataResponse(canEdit, editCall, canDelete, deleteCall, data);
        JsonObject jsonResponseObject = this.gson.toJsonTree(tableDataResponse).getAsJsonObject();

        JsonArray rawData = jsonResponseObject.get("data").getAsJsonArray();
        JsonArray filteredData = JsonUtils.filterJsonArray(rawData, fields);
        jsonResponseObject.remove("data");
        jsonResponseObject.add("data", filteredData);
        jsonResponseObject.add("data_name", new JsonPrimitive(dataName));

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.print(jsonResponseObject);
        out.flush();
    }
}
