package com.bus.servlet;

import com.bus.dao.UserDAO;
import com.bus.model.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String role = request.getParameter("role");

        UserDAO userDAO = new UserDAO();

        // Check if email already exists
        if (userDAO.emailExists(email)) {
            request.setAttribute("error", "Email already registered. Please login instead.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        User user = new User(fullName, email, password, phone, role);
        boolean success = userDAO.registerUser(user);

        if (success) {
            // Redirect to login page after successful registration
            response.sendRedirect("login.jsp");
        } else {
            request.setAttribute("error", "Registration failed. Please try again.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}