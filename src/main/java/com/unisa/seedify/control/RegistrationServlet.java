package com.unisa.seedify.control;

import com.google.gson.JsonObject;
import com.unisa.seedify.utils.InputValidation;
import com.unisa.seedify.model.EntityPrimaryKey;
import com.unisa.seedify.model.UserBean;
import com.unisa.seedify.utils.SecurityUtils;

import javax.imageio.ImageIO;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "registrationServlet", value = "/registration-servlet")
public class RegistrationServlet extends HttpServlet implements JsonServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonObject jsonBody = JsonServlet.parsePostRequestBody(request);

        String email = SecurityUtils.normalizeString(jsonBody.get("email").getAsString().toLowerCase());
        if (!InputValidation.isEmailValid(email)) {
            response.setStatus(400);
            return;
        }

        String password = jsonBody.get("password").getAsString();
        if (!InputValidation.isPasswordStrong(password)) {
            response.setStatus(400);
            return;
        }
        password = InputValidation.sha256(password);

        String name = SecurityUtils.normalizeString(jsonBody.get("name").getAsString());
        if (!InputValidation.isNameValid(name)) {
            response.setStatus(400);
            return;
        }

        String surname = SecurityUtils.normalizeString(jsonBody.get("surname").getAsString());
        if (!InputValidation.isNameValid(surname)) {
            response.setStatus(400);
            return;
        }

        String imagePath = getServletContext().getRealPath("/common/assets/img/profile/default.png");
        BufferedImage bufferedImage = ImageIO.read(new File(imagePath));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", byteArrayOutputStream);

        UserBean user = new UserBean(
            email, password,
            byteArrayOutputStream.toByteArray(),
            name, surname,
            UserBean.Roles.CUSTOMER
        );

        try {
            EntityPrimaryKey userPrimaryKey = new EntityPrimaryKey();
            userPrimaryKey.addKey("email", email);
            UserBean userBean = userDao.doRetrive(userPrimaryKey);
            if (userBean == null) {
                userDao.doSave(user);
                request.getSession(true).setAttribute("user", user);
                response.sendRedirect("home");
            } else {
                response.setStatus(401);
            }
        } catch (SQLException e) {
            response.setStatus(401);
        }
    }
}
