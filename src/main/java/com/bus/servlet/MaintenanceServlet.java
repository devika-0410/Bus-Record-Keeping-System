package com.bus.servlet;

import com.bus.dao.MaintenanceDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/MaintenanceServlet")
public class MaintenanceServlet extends HttpServlet {

    private MaintenanceDAO maintenanceDAO = new MaintenanceDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");
        Integer userId = (Integer) session.getAttribute("userId");

        String jspPath;
        if ("CONTROLLER".equals(role)) {
            request.setAttribute("reports", maintenanceDAO.getAllReports());
            jspPath = "/controller/maintenance.jsp";
        } else {
            request.setAttribute("reports", maintenanceDAO.getReportsByUser(userId));
            jspPath = "CONDUCTOR".equals(role) ? "/conductor/maintenance.jsp" : "/driver/maintenance.jsp";
        }
        request.getRequestDispatcher(jspPath).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");
        Integer userId = (Integer) session.getAttribute("userId");
        String action = request.getParameter("action");

        if ("updateStatus".equals(action)) {
            // Only Controller should do this (Section 1: Controller updates status)
            int reportId = Integer.parseInt(request.getParameter("reportId"));
            String status = request.getParameter("status");
            maintenanceDAO.updateStatus(reportId, status, userId);
        } else {
            // "report" - Conductor/Driver submitting a new issue
            int busId = Integer.parseInt(request.getParameter("busId"));
            String description = request.getParameter("issueDescription");
            maintenanceDAO.reportIssue(busId, userId, description);
        }

        response.sendRedirect("MaintenanceServlet");
    }
}