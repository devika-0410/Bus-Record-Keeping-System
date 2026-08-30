package com.bus.servlet;

import com.bus.dao.TicketDAO;
import com.bus.model.Ticket;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/MyTicketsServlet")
public class MyTicketsServlet extends HttpServlet {

    private TicketDAO ticketDAO = new TicketDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Integer passengerId = (Integer) session.getAttribute("userId");

        List<Ticket> tickets = ticketDAO.getTicketsByPassenger(passengerId);
        request.setAttribute("tickets", tickets);

        request.getRequestDispatcher("/passenger/my_tickets.jsp").forward(request, response);
    }
}