package com.unisa.seedify.control;

import com.unisa.seedify.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "addressServlet", urlPatterns = {"/address-servlet"})
public class AddressServlet  extends HttpServlet implements JsonServlet {
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
            case "add_address": {
                try {
                    String street = request.getParameter("street");
                    String city = request.getParameter("city");
                    String zipCode = request.getParameter("zip_code");
                    String province = request.getParameter("province");
                    String firstName = request.getParameter("first_name");
                    String lastName = request.getParameter("last_name");
                    String phone = request.getParameter("phone");
                    String notes = request.getParameter("notes");

                    AddressBean addressBean = new AddressBean(province, city, zipCode, street, firstName, lastName, phone, notes);

                    try {
                        addressDao.doSave(addressBean);
                    } catch (SQLException e) {
                        System.out.println("An error occurred while processing the request" + e.getMessage());
                    }

                    ArrayList<AddressBean> addresses = new ArrayList<>();
                    addresses.add(addressBean);
                    LocationsBean locationsBean = new LocationsBean(userBean, addresses);
                    locationsDao.doSave(locationsBean);

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
