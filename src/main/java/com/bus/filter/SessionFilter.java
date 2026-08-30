package com.bus.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebFilter(urlPatterns = {"/controller/*", "/conductor/*", "/driver/*", "/passenger/*"})
public class SessionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false); // false = don't create a new session

        if (session == null || session.getAttribute("user") == null) {
            // Not logged in - redirect to login page
            res.sendRedirect(req.getContextPath() + "/login.jsp");
        } else {
            // Logged in - continue to the requested page
            chain.doFilter(request, response);
        }
    }
}