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
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.*;

@WebServlet(name="userServlet", value="/user-servlet")
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
        fields.add("entity_primary_key");

        HttpSession session = request.getSession();
        UserBean userBean = (UserBean) session.getAttribute("user");

        boolean canEdit = false;
        String editCall = null;
        boolean canDelete = false;
        String deleteCall = null;

        ArrayList<Object> data = null;
        if (userBean.getRole().equals(UserBean.Roles.ADMIN)) {
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
                    editCall = "";
                    canDelete = true;
                    deleteCall = "delete_product";
                    data = new ArrayList<>(productDao.getAllActiveProducts());
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
                    break;
                }
                case "get_favorites": {
                    /*data = new ArrayList<>(orderDao.getAllOrders(userBean));*/
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

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.print(jsonResponseObject);
        out.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        HttpSession session = request.getSession();
        UserBean userBean = (UserBean) session.getAttribute("user");

        if (userBean.getRole().equals(UserBean.Roles.ADMIN)) {
            switch (action) {
                case "delete_product": {
                    this.deleteProduct(request, response);
                    break;
                }
                default: {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
                }
            }
        }
    }
}
