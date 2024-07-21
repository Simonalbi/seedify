package com.unisa.seedify.control;

import com.unisa.seedify.utils.InputValidation;
import com.unisa.seedify.model.EntityPrimaryKey;
import com.unisa.seedify.model.UserBean;

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

// TODO: Validare l'input e in caso di errore far apparire nella pagina l'errore
// TODO Redirect instead of throwing exception
@WebServlet(name = "registrationServlet", value = "/registration-servlet")
public class RegistrationServlet extends HttpServlet implements JsonServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email").toLowerCase();
        if (!InputValidation.isEmailValid(email)) {
            throw new IllegalArgumentException("Invalid email");
        }

        String password = request.getParameter("password");
        if (!InputValidation.isPasswordStrong(password)) {
            throw new IllegalArgumentException("Password is not strong enough");
        }
        password = InputValidation.sha256(password);

        String name = request.getParameter("name");
        if (!InputValidation.isNameValid(name)) {
            throw new IllegalArgumentException("Invalid name");
        }

        String surname = request.getParameter("surname");
        if (!InputValidation.isNameValid(surname)) {
            throw new IllegalArgumentException("Invalid surname");
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
                throw new IllegalArgumentException("User already exists");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
