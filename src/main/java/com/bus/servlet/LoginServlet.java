package com.bus.servlet;

import com.bus.dao.UserDAO;
import com.bus.model.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDAO userDAO = new UserDAO();
        User user = userDAO.validateLogin(email, password);

        if (user != null) {
            // Create a session and store user info
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("role", user.getRole());
            session.setAttribute("userId", user.getUserId());

            // Redirect based on role
            switch (user.getRole()) {
                case "CONTROLLER":
                    response.sendRedirect("controller/dashboard.jsp");
                    break;
                case "CONDUCTOR":
                    response.sendRedirect("conductor/dashboard.jsp");
                    break;
                case "DRIVER":
                    response.sendRedirect("driver/dashboard.jsp");
                    break;
                case "PASSENGER":
                    response.sendRedirect("passenger/dashboard.jsp");
                    break;
                default:
                    response.sendRedirect("login.jsp");
            }
        } else {
            request.setAttribute("error", "Invalid email or password.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}