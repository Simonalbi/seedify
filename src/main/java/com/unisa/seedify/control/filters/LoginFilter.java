package com.unisa.seedify.control.filters;

import com.unisa.seedify.model.UserBean;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        UserBean userBean = (UserBean) httpRequest.getSession(true).getAttribute("user");
        if (userBean == null) {
            httpResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, "User not logged in");
            return;
        }

        chain.doFilter(request, response);
    }
}
