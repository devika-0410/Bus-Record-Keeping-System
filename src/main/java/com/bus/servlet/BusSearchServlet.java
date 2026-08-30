package com.bus.servlet;

import com.bus.dao.RouteDAO;
import com.bus.dao.TimetableDAO;
import com.bus.dao.TicketDAO;
import com.bus.model.Route;
import com.bus.model.Timetable;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/BusSearchServlet")
public class BusSearchServlet extends HttpServlet {

    private RouteDAO routeDAO = new RouteDAO();
    private TimetableDAO timetableDAO = new TimetableDAO();
    private TicketDAO ticketDAO = new TicketDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Route> routes = routeDAO.getAllRoutes();
        request.setAttribute("routes", routes);

        String routeIdParam = request.getParameter("routeId");
        String dateParam = request.getParameter("travelDate");

        // Only search if the passenger actually submitted the form
        if (routeIdParam != null && dateParam != null && !routeIdParam.isEmpty() && !dateParam.isEmpty()) {
            int routeId = Integer.parseInt(routeIdParam);
            Date travelDate = Date.valueOf(dateParam);

            List<Timetable> results = timetableDAO.searchTimetables(routeId, travelDate);

            // Attach available seat count to each result (done via a small helper map)
            for (Timetable t : results) {
                int booked = ticketDAO.countBookedSeats(t.getTimetableId());
                int available = t.getTotalSeats() - booked;
                t.setAvailableSeats(available); // we'll add this field below
            }

            request.setAttribute("results", results);
            request.setAttribute("searched", true);
        }

        request.getRequestDispatcher("/passenger/search_bus.jsp").forward(request, response);
    }
}