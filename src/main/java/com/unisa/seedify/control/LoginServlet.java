package com.unisa.seedify.control;

import com.unisa.seedify.model.CartBean;
import com.unisa.seedify.utils.InputValidation;
import com.unisa.seedify.model.EntityPrimaryKey;
import com.unisa.seedify.model.UserBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

// TODO Accept AJAX request for input validation
@WebServlet(name = "loginServlet", value = "/login-servlet")
public class LoginServlet extends HttpServlet implements JsonServlet {
    private void initSession(HttpServletRequest request, HttpServletResponse response, UserBean userBean) throws ServletException, SQLException {
        HttpSession session = request.getSession(true);
        session.setAttribute("user", userBean);

        EntityPrimaryKey cartPrimaryKey = new EntityPrimaryKey();
        cartPrimaryKey.addKey("email", userBean.getEmail());
        CartBean cartBean = cartDao.doRetrive(cartPrimaryKey);
        session.setAttribute("cart", cartBean);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession(true).invalidate();
        response.sendRedirect("home");
    }

    // TODO Redirect instead of throwing exception
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email").trim().toLowerCase();
        if (!InputValidation.isEmailValid(email)) {
            throw new IllegalArgumentException("Invalid email");
        }

        String password = request.getParameter("password");
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
                response.sendRedirect("dashboard");
            } catch (ServletException | SQLException e) {
                response.sendRedirect("/common/errors/404.jsp");
            }
        } else {
            throw new IllegalArgumentException("Invalid email or password");
        }
    }
}
