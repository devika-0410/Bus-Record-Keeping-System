package com.bus.servlet;

import com.bus.dao.TicketDAO;
import com.bus.model.Ticket;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/AllTicketsServlet")
public class AllTicketsServlet extends HttpServlet {

    private TicketDAO ticketDAO = new TicketDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Ticket> tickets = ticketDAO.getAllTickets();
        request.setAttribute("tickets", tickets);
        request.getRequestDispatcher("/controller/tickets.jsp").forward(request, response);
    }
}