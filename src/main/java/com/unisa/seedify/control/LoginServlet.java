package com.unisa.seedify.control;

import com.unisa.seedify.control.utils.InputValidation;
import com.unisa.seedify.model.EntityPrimaryKey;
import com.unisa.seedify.model.UserBean;
import com.unisa.seedify.model.UserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

// TODO Ajax per inviare i messaggi di errore al client
@WebServlet(name = "loginServlet", value = "/login-servlet")
public class LoginServlet extends HttpServlet {
    private static final UserDao userDao = UserDao.getInstance();

    public void init() {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email").trim().toLowerCase();
        if (!InputValidation.isEmailValid(email)) {
            throw new IllegalArgumentException("Invalid email");
        }

        String password = request.getParameter("password");
        if (!InputValidation.isPasswordStrong(password)) {
            throw new IllegalArgumentException("Password is not strong enough");
        }

        EntityPrimaryKey userPrimaryKey = new EntityPrimaryKey();
        userPrimaryKey.addKey("email", email);

        UserBean user = null;
        try {
            user = userDao.doRetrive(userPrimaryKey);
            switch (user.getRole()) {
                case ADMIN:
                    response.sendRedirect("admin/admin.jsp");
                    break;
                case EMPLOYEE:
                    // TODO Redirect to employee page
                    break;
                case CLIENT:
                    // TODO Redirect to client page
                    break;
                default:
                    throw new IllegalArgumentException("Invalid role");
            }
            // TODO: Get role from user and redirect to correct page
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (user == null) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        System.out.println("User " + user.getEmail() + " logged in");
    }

    public void destroy() {
    }
}
