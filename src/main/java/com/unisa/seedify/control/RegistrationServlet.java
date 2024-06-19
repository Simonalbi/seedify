package com.unisa.seedify.control;

import com.unisa.seedify.control.utils.InputValidation;
import com.unisa.seedify.model.UserBean;
import com.unisa.seedify.model.UserDao;

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

// TODO: Implementare un metodo per controllare se un email è già in uso
// TODO: Validare l'input e in caso di errore far apparire nella pagina l'errore
@WebServlet(name = "registrationServlet", value = "/registration-servlet")
public class RegistrationServlet extends HttpServlet {
    private static final UserDao userDao = UserDao.getInstance();

    public void init() {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserBean user = new UserBean();

        String email = request.getParameter("email").toLowerCase();
        if (!InputValidation.isEmailValid(email)) {
            throw new IllegalArgumentException("Invalid email");
        }

        String password = request.getParameter("password");
        if (!InputValidation.isPasswordStrong(password)) {
            throw new IllegalArgumentException("Password is not strong enough");
        }

        String name = request.getParameter("name");
        if (!InputValidation.isNameValid(name)) {
            throw new IllegalArgumentException("Invalid name");
        }

        String surname = request.getParameter("surname");
        if (!InputValidation.isNameValid(surname)) {
            throw new IllegalArgumentException("Invalid surname");
        }

        user.setEmail(email);
        user.setPassword(InputValidation.sha256(password));
        user.setName(name);
        user.setSurname(surname);
        user.setRole(UserBean.Roles.CLIENT);

        String imagePath = getServletContext().getRealPath("/common/assets/img/profile/default.png");
        BufferedImage bufferedImage = ImageIO.read(new File(imagePath));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
        user.setProfilePicture(byteArrayOutputStream.toByteArray());

        try {
            userDao.doSave(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void destroy() {
    }
}
