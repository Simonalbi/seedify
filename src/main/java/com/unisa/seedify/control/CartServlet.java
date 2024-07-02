package com.unisa.seedify.control;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.unisa.seedify.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "cartServlet", urlPatterns = {"/cart-servlet"})
public class CartServlet extends HttpServlet implements JsonServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonObject jsonBody = JsonServlet.parsePostRequestBody(request);

        HttpSession httpSession = request.getSession(true);
        CartBean cartBean = (CartBean) httpSession.getAttribute("cart");

        String action = null;
        try {
            action = jsonBody.get("action").getAsString();
        } catch (NullPointerException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing param 'action' in request body");
            return;
        }

        int productId;
        try {
            productId = jsonBody.get("product_id").getAsInt();
        } catch (NullPointerException | JsonSyntaxException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing param 'product_id' in request body");
            return;
        }

        ProductBean productBean = null;
        try {
            EntityPrimaryKey productPrimaryKey = new EntityPrimaryKey();
            productPrimaryKey.addKey("codice_prodotto", productId);
            productBean = productDao.doRetrive(productPrimaryKey);
        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing the request");
            return;
        }

        boolean success = false;
        switch (action) {
            case "add_to_cart": {
                try {
                    int quantity = jsonBody.get("quantity").getAsInt();
                    success = cartDao.addToCart(cartBean, productBean, quantity);
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
