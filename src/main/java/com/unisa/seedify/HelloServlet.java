package com.unisa.seedify;

import com.unisa.seedify.model.UserBean;
import com.unisa.seedify.model.UserDao;

import java.io.*;
import java.sql.SQLException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Just testing the connection to the database
        System.out.println("Inizio");

        UserBean user = new UserBean();
        user.setEmail("a");
        user.setPassword("b");
        user.setName("c");
        user.setSurname("d");
        user.setRole(UserBean.Roles.CLIENT);
        user.setProfilePicture(new byte[0]);

        try {
            UserDao userDao = new UserDao();
            userDao.doSave(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void destroy() {
    }
}