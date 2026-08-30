package com.bus.servlet;

import com.bus.dao.RouteDAO;
import com.bus.dao.TicketDAO;
import com.bus.dao.TimetableDAO;
import com.bus.model.Route;
import com.bus.model.Timetable;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/BookTicketServlet")
public class BookTicketServlet extends HttpServlet {

    private static final double RATE_PER_KM = 2.0; // simple fare formula

    private TicketDAO ticketDAO = new TicketDAO();
    private TimetableDAO timetableDAO = new TimetableDAO();
    private RouteDAO routeDAO = new RouteDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Integer passengerId = (Integer) session.getAttribute("userId");

        int timetableId = Integer.parseInt(request.getParameter("timetableId"));
        int seatNumber = Integer.parseInt(request.getParameter("seatNumber"));

        // Step 1: Re-check seat availability (prevents double-booking from concurrent clicks)
        if (ticketDAO.isSeatBooked(timetableId, seatNumber)) {
            request.setAttribute("error", "Sorry, seat " + seatNumber + " was just booked by someone else. Please choose another seat.");
            request.getRequestDispatcher("/passenger/booking_failed.jsp").forward(request, response);
            return;
        }

        // Step 2: Get trip details to calculate fare and store correct info
        Timetable timetable = timetableDAO.getTimetableById(timetableId);
        if (timetable == null) {
            request.setAttribute("error", "Trip not found.");
            request.getRequestDispatcher("/passenger/booking_failed.jsp").forward(request, response);
            return;
        }

        Route route = routeDAO.getRouteById(timetable.getRouteId());
        double fare = route.getDistanceKm() * RATE_PER_KM;

        // Step 3: Book the ticket (insert into DB, seat now reserved)
        int ticketId = ticketDAO.bookTicket(
                passengerId,
                timetable.getBusId(),
                timetableId,
                seatNumber,
                timetable.getTravelDate(),
                fare
        );

        if (ticketId == -1) {
            request.setAttribute("error", "Booking failed. Please try again.");
            request.getRequestDispatcher("/passenger/booking_failed.jsp").forward(request, response);
            return;
        }

        // Step 4: Success - redirect to payment page (Step 11 builds this properly)
        response.sendRedirect("PaymentServlet?ticketId=" + ticketId);
    }
}