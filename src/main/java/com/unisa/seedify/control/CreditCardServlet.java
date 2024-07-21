package com.unisa.seedify.control;

import com.unisa.seedify.model.*;
import com.unisa.seedify.utils.SecurityUtils;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
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

// TODO Memorizzare i dati in modo sicuro
@WebServlet(name = "creditCardServlet", urlPatterns = {"/credit-card-servlet"})
public class CreditCardServlet extends HttpServlet implements JsonServlet {
    private static String ENCRYPTION_KEY = "";

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            InitialContext initialContext = new InitialContext();
            Context environmentContext = (Context) initialContext.lookup("java:/comp/env");
            ENCRYPTION_KEY = (String) environmentContext.lookup("dataEncryptionKey");
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

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
                    String creditCardNumber = request.getParameter("credit_card_number");
                    String cvv = request.getParameter("cvv");
                    Date expirationDate = Date.valueOf(request.getParameter("expiration_date"));
                    String name = request.getParameter("name");
                    String surname = request.getParameter("surname");

                    CreditCardBean creditCardBean = new CreditCardBean(
                        SecurityUtils.encrypt(creditCardNumber, ENCRYPTION_KEY),
                        SecurityUtils.encrypt(cvv, ENCRYPTION_KEY),
                        expirationDate, name, surname);

                    List<CreditCardBean> creditCardBeans = new ArrayList<>();
                    creditCardBeans.add(creditCardBean);
                    MemorizationsBean memorizationsBean = new MemorizationsBean(userBean, creditCardBeans);

                    try {
                        creditCardDao.doSave(creditCardBean);
                    } catch (SQLException ignored) {}

                    memorizationsDao.doSave(memorizationsBean);

                    success = true;
                } catch (SQLException e) {
                    System.out.println("An error occurred while processing the request");
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
