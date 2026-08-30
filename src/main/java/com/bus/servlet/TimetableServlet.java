package com.bus.servlet;

import com.bus.dao.TimetableDAO;
import jakarta.servlet.http.HttpSession;
import com.bus.model.Timetable;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@WebServlet("/TimetableServlet")
public class TimetableServlet extends HttpServlet {

    private TimetableDAO timetableDAO = new TimetableDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Timetable> timetables = timetableDAO.getAllTimetables();
        request.setAttribute("timetables", timetables);

        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        String jspPath;
        switch (role) {
            case "CONTROLLER": jspPath = "/controller/timetable.jsp"; break;
            case "CONDUCTOR": jspPath = "/conductor/timetable.jsp"; break;
            case "DRIVER": jspPath = "/driver/timetable.jsp"; break;
            default: jspPath = "/login.jsp";
        }
        request.getRequestDispatcher(jspPath).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action"); // "create" or "update"

        Timetable t = new Timetable();
        t.setBusId(Integer.parseInt(request.getParameter("busId")));
        t.setRouteId(Integer.parseInt(request.getParameter("routeId")));
        t.setTravelDate(Date.valueOf(request.getParameter("travelDate")));
        t.setScheduledArrival(Time.valueOf(request.getParameter("scheduledArrival") + ":00"));
        t.setScheduledDeparture(Time.valueOf(request.getParameter("scheduledDeparture") + ":00"));

        boolean success;

        if ("update".equals(action)) {
            t.setTimetableId(Integer.parseInt(request.getParameter("timetableId")));
            success = timetableDAO.updateTimetable(t);
        } else {
            // "create"
            Integer userId = (Integer) request.getSession().getAttribute("userId");
            t.setCreatedBy(userId);
            success = timetableDAO.createTimetable(t);
        }

        // Redirect back to the GET view (avoids resubmission on refresh)
        response.sendRedirect(request.getContextPath() + "/TimetableServlet");
    }
}