package com.unisa.seedify.control;

import com.unisa.seedify.model.EntityPrimaryKey;
import com.unisa.seedify.model.UserBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

@WebServlet(name = "resourcesServlet", value = "/resources-servlet")
public class ResourcesServlet extends HttpServlet {
    private byte[] getSessionUserProfilePicture(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        UserBean userBean = (UserBean) session.getAttribute("user");

        // TODO Auto identify file type
        response.setContentType("image/png");

        return userBean.getProfilePicture();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String resourceType = request.getParameter("resourceType");

        byte[] resource = null;
        switch (resourceType) {
            case "profile_picture": {
                resource = this.getSessionUserProfilePicture(request, response);
                break;
            }
        }

        if (resource != null) {
            response.setContentLength(resource.length);
            try (OutputStream out = response.getOutputStream()) {
                out.write(resource);
                out.flush();
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid resource type");
        }
    }
}
