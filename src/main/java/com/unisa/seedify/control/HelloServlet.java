package com.unisa.seedify.control;

import com.unisa.seedify.model.UserBean;
import com.unisa.seedify.model.UserDao;

import java.io.*;
import java.sql.SQLException;
import javax.servlet.ServletException;
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
        System.out.println("HELLO");

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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    public void destroy() {
    }
}