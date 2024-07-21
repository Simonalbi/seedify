package com.unisa.seedify.control;

import com.unisa.seedify.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "orderServlet", urlPatterns = {"/order-servlet"})
public class OrderServlet extends HttpServlet implements JsonServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserBean userBean = (UserBean) request.getSession(true).getAttribute("user");
        CartBean cartBean = (CartBean) request.getSession(true).getAttribute("cart");

        boolean success = false;
        try {
            int addressId = Integer.parseInt(request.getParameter("address_code"));
            int creditCardId = Integer.parseInt(request.getParameter("credit_card_code"));

            EntityPrimaryKey creditCardPrimaryKey = new EntityPrimaryKey();
            creditCardPrimaryKey.addKey("codice_carta", creditCardId);
            CreditCardBean creditCardBean = creditCardDao.doRetrive(creditCardPrimaryKey);

            EntityPrimaryKey addressPrimaryKey = new EntityPrimaryKey();
            addressPrimaryKey.addKey("codice_indirizzo", addressId);
            AddressBean addressBean = addressDao.doRetrive(addressPrimaryKey);

            OrderBean orderBean = new OrderBean(
                    creditCardBean,
                    userBean,
                    addressBean,
                    new Date(System.currentTimeMillis()),
                    cartBean.getTotalCartPrice()
            );

            orderDao.doSave(orderBean);
            cartDao.emptyCart(cartBean);

            success = true;

        } catch (SQLException e) {
            System.out.println("An error occurred while processing the request" + e.getMessage());
        }

        if (success) {
            response.sendRedirect("dashboard");
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "An error occurred while processing the request");
        }
    }
}
