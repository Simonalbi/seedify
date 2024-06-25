package com.unisa.seedify.control;

import com.unisa.seedify.control.utils.InputValidation;
import com.unisa.seedify.model.EntityPrimaryKey;
import com.unisa.seedify.model.UserBean;
import com.unisa.seedify.model.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

// TODO Accept AJAX request for input validation
@WebServlet(name = "loginServlet", value = "/login-servlet")
public class LoginServlet extends HttpServlet {
    private static final UserDao userDao = UserDao.getInstance();

    public void init() {
    }

    private void redirectUser(HttpServletRequest request, HttpServletResponse response, UserBean.Roles role) throws ServletException, IOException {
        switch (role) {
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
    }

    private void initSession(HttpServletRequest request, HttpServletResponse response, UserBean user) throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        UUID uuid = UUID.randomUUID();
        session.setAttribute("token", uuid.toString());

        session.setAttribute("user", user);
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
        password = InputValidation.sha256(password);

        EntityPrimaryKey userPrimaryKey = new EntityPrimaryKey();
        userPrimaryKey.addKey("email", email);

        UserBean user = null;
        try {
            user = userDao.doRetrive(userPrimaryKey);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (user == null) {
            throw new IllegalArgumentException("Invalid email or password");
        } else if (user.getPassword().equals(password)) {
            try {
                this.initSession(request, response, user);
                this.redirectUser(request, response, user.getRole());
            } catch (ServletException e) {
                response.sendRedirect("/common/errors/404.jsp");
            }
        } else {
            throw new IllegalArgumentException("Invalid email or password");
        }

        // DUBUG
        System.out.println("User " + user.getEmail() + " logged in");
    }

    public void destroy() {
    }
}
