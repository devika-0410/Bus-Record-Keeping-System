package com.bus.servlet;

import com.bus.dao.BusTimingDAO;
import com.bus.dao.TimetableDAO;
import com.bus.model.BusTiming;
import com.bus.model.Timetable;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.Time;
import java.util.List;

@WebServlet("/BusTimingServlet")
public class BusTimingServlet extends HttpServlet {

    private BusTimingDAO busTimingDAO = new BusTimingDAO();
    private TimetableDAO timetableDAO = new TimetableDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        List<Timetable> timetables = timetableDAO.getAllTimetables();
        List<BusTiming> timings = busTimingDAO.getAllTimings();

        request.setAttribute("timetables", timetables);
        request.setAttribute("timings", timings);

        String jspPath;
        switch (role) {
            case "CONTROLLER": jspPath = "/controller/bus_timing.jsp"; break;
            case "CONDUCTOR": jspPath = "/conductor/bus_timing.jsp"; break;
            case "DRIVER": jspPath = "/driver/bus_timing.jsp"; break;
            default: jspPath = "/login.jsp";
        }
        request.getRequestDispatcher(jspPath).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        String type = request.getParameter("type"); // "arrival" or "departure"
        int timetableId = Integer.parseInt(request.getParameter("timetableId"));
        Time actualTime = Time.valueOf(request.getParameter("actualTime") + ":00");

        if ("arrival".equals(type)) {
            busTimingDAO.recordArrival(timetableId, actualTime, userId);
        } else {
            busTimingDAO.recordDeparture(timetableId, actualTime, userId);
        }

        response.sendRedirect("BusTimingServlet");
    }
}