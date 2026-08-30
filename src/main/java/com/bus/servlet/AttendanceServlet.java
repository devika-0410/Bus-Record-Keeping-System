package com.bus.servlet;

import com.bus.dao.AttendanceDAO;
import com.bus.model.Attendance;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/AttendanceServlet")
public class AttendanceServlet extends HttpServlet {

    private AttendanceDAO attendanceDAO = new AttendanceDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");
        Integer userId = (Integer) session.getAttribute("userId");

        if ("CONTROLLER".equals(role)) {
            // Controller sees everyone's attendance
            List<Attendance> allAttendance = attendanceDAO.getAllAttendance();
            request.setAttribute("attendanceList", allAttendance);
            request.getRequestDispatcher("/controller/attendance.jsp").forward(request, response);
        } else {
            // Conductor/Driver sees only their own attendance
            List<Attendance> myAttendance = attendanceDAO.getAttendanceByUser(userId);
            request.setAttribute("attendanceList", myAttendance);

            String jspPath = "CONDUCTOR".equals(role) ? "/conductor/attendance.jsp" : "/driver/attendance.jsp";
            request.getRequestDispatcher(jspPath).forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        int busId = Integer.parseInt(request.getParameter("busId"));
        Date attendanceDate = Date.valueOf(request.getParameter("attendanceDate"));

        Attendance a = new Attendance();
        a.setUserId(userId);
        a.setBusId(busId);
        a.setAttendanceDate(attendanceDate);
        a.setStatus("PRESENT"); // marking attendance always means present

        attendanceDAO.markAttendance(a);

        response.sendRedirect("AttendanceServlet");
    }
}