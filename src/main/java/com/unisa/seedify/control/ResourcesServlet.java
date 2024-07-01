package com.unisa.seedify.control;

import com.unisa.seedify.model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.sql.SQLException;

@WebServlet(name = "resourcesServlet", value = "/resources-servlet")
public class ResourcesServlet extends HttpServlet implements JsonServlet {
    private byte[] getSessionUserProfilePicture(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        UserBean userBean = (UserBean) session.getAttribute("user");
        return userBean.getProfilePicture();
    }

    private byte[] getProductImage(HttpServletRequest request, HttpServletResponse response) {
        String rawProductPrimaryKey = request.getParameter("entity_primary_key");
        if (rawProductPrimaryKey == null) {
            return null;
        }

        try {
            EntityPrimaryKey productPrimaryKey = BaseBean.parsePrimaryKey(rawProductPrimaryKey);
            ProductBean productBean = productDao.doRetrive(productPrimaryKey);
            return productBean.getImage();
        } catch (SQLException ignored) {
            return null;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String resourceType = request.getParameter("resource_type");

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
            case "product_page": {
                int productId = Integer.parseInt(request.getParameter("product_id"));

                ProductBean productBean = null;
                ProductDao productDao = ProductDao.getInstance();
                EntityPrimaryKey entityPrimaryKey = new EntityPrimaryKey();
                entityPrimaryKey.addKey("codice_prodotto", productId);
                try {
                    productBean = productDao.doRetrive(entityPrimaryKey);
                } catch (SQLException ignored) {}

                if (productBean == null) {
                    response.sendRedirect(request.getContextPath() + "/common/error/404.jsp");
                }

                RequestDispatcher dispatcher = request.getRequestDispatcher("product/product.jsp");
                request.setAttribute("product_bean", productBean);
                dispatcher.forward(request, response);
                return;
            }
            default: {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid resource type!");
                return;
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
