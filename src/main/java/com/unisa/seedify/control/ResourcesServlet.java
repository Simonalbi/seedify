package com.unisa.seedify.control;

import com.unisa.seedify.model.EntityPrimaryKey;
import com.unisa.seedify.model.ProductBean;
import com.unisa.seedify.model.ProductDao;
import com.unisa.seedify.model.UserBean;
import org.apache.tika.Tika;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

@WebServlet(name = "resourcesServlet", value = "/resources-servlet")
public class ResourcesServlet extends HttpServlet {
    private static final Tika tika = new Tika();

    private static final ProductDao productDao = ProductDao.getInstance();

    private byte[] getSessionUserProfilePicture(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        UserBean userBean = (UserBean) session.getAttribute("user");
        return userBean.getProfilePicture();
    }

    private byte[] getProductImage(HttpServletRequest request, HttpServletResponse response) {
        String rawProductPrimaryKey = request.getParameter("entity_primary_key");
        if (rawProductPrimaryKey == null) {
            return null;
        }

        EntityPrimaryKey productPrimaryKey = new EntityPrimaryKey();
        ArrayList<String> productPrimaryKeyValues = new ArrayList<>(Arrays.asList(rawProductPrimaryKey.split(",")));
        for (String value : productPrimaryKeyValues) {
            String[] keyValue = value.split("=");

            Object effectiveValue;
            try {
                effectiveValue = Integer.parseInt(keyValue[1]);
            } catch (NumberFormatException ignored) {
                try {
                    effectiveValue = Float.parseFloat(keyValue[1]);
                } catch (NumberFormatException ignored2) {
                    effectiveValue = keyValue[1];
                }
            }

            productPrimaryKey.addKey(keyValue[0], effectiveValue);
        }

        try {
            ProductBean productBean = productDao.doRetrive(productPrimaryKey);
            return productBean.getImage();
        } catch (SQLException ignored) {
            return null;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String resourceType = request.getParameter("resourceType");

        byte[] resource = null;
        switch (resourceType) {
            case "profile_picture": {
                resource = this.getSessionUserProfilePicture(request, response);
                break;
            }
            case "product_image": {
                resource = this.getProductImage(request, response);
                break;
            }
        }

        if (resource != null) {
            String mimeType = tika.detect(resource);
            response.setContentType(mimeType);
            response.setContentLength(resource.length);

            try (OutputStream out = response.getOutputStream()) {
                out.write(resource);
                out.flush();
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid resource!");
        }
    }
}
