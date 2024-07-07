package com.unisa.seedify.control;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.unisa.seedify.model.BaseBean;
import com.unisa.seedify.model.EntityPrimaryKey;
import com.unisa.seedify.model.ProductBean;
import com.unisa.seedify.model.UserBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "favoritesServlet", urlPatterns = {"/favorites-servlet"})
public class FavoritesServlet extends HttpServlet implements JsonServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonObject jsonBody = JsonServlet.parsePostRequestBody(request);

        String action = null;
        try {
            action = jsonBody.get("action").getAsString();
        } catch (NullPointerException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing param 'action' in request body");
            return;
        }

        ProductBean productBean;
        try {
            int productId = jsonBody.get("product_id").getAsInt();
            EntityPrimaryKey productPrimaryKey = new EntityPrimaryKey();
            productPrimaryKey.addKey("codice_prodotto", productId);
            productBean = productDao.doRetrive(productPrimaryKey);
        } catch (NullPointerException | JsonSyntaxException | SQLException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing param 'product_id' in request body");
            return;
        }

        UserBean userBean = (UserBean) request.getSession(true).getAttribute("user");
        boolean success = false;
        switch (action) {
            case "add_to_favorites": {
                try {
                    success = favoritesDao.addToFavorites(userBean, productBean);
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
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        UserBean userBean = (UserBean) session.getAttribute("user");

        String action = request.getParameter("action");
        if (action == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing parameter 'action'");
            return;
        }

        switch (action) {
            case "remove_from_favorites": {
                try {
                    int productId = Integer.parseInt(request.getParameter("product_id"));

                    EntityPrimaryKey productPrimaryKey = new EntityPrimaryKey();
                    productPrimaryKey.addKey("codice_prodotto", productId);

                    ProductBean productBean = productDao.doRetrive(productPrimaryKey);
                    favoritesDao.removeFromFavorites(userBean, productBean);
                } catch (SQLException ignored) {}
                break;
            }
            default: {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
            }
        }
    }
}
