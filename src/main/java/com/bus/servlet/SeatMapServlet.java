package com.bus.servlet;

import com.bus.dao.TicketDAO;
import com.bus.dao.TimetableDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/SeatMapServlet")
public class SeatMapServlet extends HttpServlet {

    private TicketDAO ticketDAO = new TicketDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int timetableId = Integer.parseInt(request.getParameter("timetableId"));
        int totalSeats = Integer.parseInt(request.getParameter("totalSeats"));

        List<Integer> bookedSeats = ticketDAO.getBookedSeatNumbers(timetableId);

        request.setAttribute("timetableId", timetableId);
        request.setAttribute("totalSeats", totalSeats);
        request.setAttribute("bookedSeats", bookedSeats);

        request.getRequestDispatcher("/passenger/seat_map.jsp").forward(request, response);
    }
}