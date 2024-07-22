package com.unisa.seedify.control;

import com.unisa.seedify.model.*;
import com.unisa.seedify.utils.SecurityUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "creditCardServlet", urlPatterns = {"/credit-card-servlet"})
public class CreditCardServlet extends HttpServlet implements JsonServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserBean userBean = (UserBean) request.getSession(true).getAttribute("user");

        String action = request.getParameter("action");
        if (action == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing parameter 'action'");
            return;
        }

        boolean success = false;
        switch (action) {
            case "add_credit_card": {
                try {
                    String creditCardNumber = SecurityUtils.normalizeString(request.getParameter("credit_card_number"));
                    String cvv = SecurityUtils.normalizeString(request.getParameter("cvv"));
                    Date expirationDate = Date.valueOf(request.getParameter("expiration_date"));
                    String name = SecurityUtils.normalizeString(request.getParameter("name"));
                    String surname = SecurityUtils.normalizeString(request.getParameter("surname"));

                    CreditCardBean creditCardBean = new CreditCardBean(creditCardNumber, cvv, expirationDate, name, surname);

                    List<CreditCardBean> creditCardBeans = new ArrayList<>();
                    creditCardBeans.add(creditCardBean);
                    MemorizationsBean memorizationsBean = new MemorizationsBean(userBean, creditCardBeans);

                    try {
                        creditCardDao.doSave(creditCardBean);
                    } catch (SQLException e) {
                        System.out.println("An error occurred while processing the request" + e.getMessage());
                    }

                    memorizationsDao.doSave(memorizationsBean);

                    success = true;
                } catch (SQLException e) {
                    System.out.println("An error occurred while processing the request" + e.getMessage());
                }
                break;
            }
            default: {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
            }
        }

        if (success) {
            response.sendRedirect("dashboard");
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "An error occurred while processing the request");
        }
    }
}
