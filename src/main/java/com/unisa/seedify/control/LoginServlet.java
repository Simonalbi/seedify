package com.unisa.seedify.control;

import com.unisa.seedify.control.utils.InputValidation;
import com.unisa.seedify.model.EntityPrimaryKey;
import com.unisa.seedify.model.UserBean;
import com.unisa.seedify.model.UserDao;

import javax.imageio.ImageIO;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.Console;
import java.io.File;
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
        userPrimaryKey.addKey("password", InputValidation.sha256(password));

        UserBean user = null;
        try {
            user = userDao.doRetrive(userPrimaryKey);
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
