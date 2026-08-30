package com.bus.servlet;

import com.bus.dao.PaymentDAO;
import com.bus.dao.TicketDAO;
import com.bus.model.Ticket;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/TicketViewServlet")
public class TicketViewServlet extends HttpServlet {

    private TicketDAO ticketDAO = new TicketDAO();
    private PaymentDAO paymentDAO = new PaymentDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Integer loggedInUserId = (Integer) session.getAttribute("userId");
        String role = (String) session.getAttribute("role");

        int ticketId = Integer.parseInt(request.getParameter("ticketId"));
        Ticket ticket = ticketDAO.getTicketById(ticketId);

        if (ticket == null) {
            response.sendRedirect("MyTicketsServlet");
            return;
        }

        // Security check: only the ticket's owner OR a Controller can view it
        boolean isOwner = ticket.getPassengerId() == loggedInUserId;
        boolean isController = "CONTROLLER".equals(role);

        if (!isOwner && !isController) {
            request.setAttribute("error", "You are not authorized to view this ticket.");
            request.getRequestDispatcher("/passenger/booking_failed.jsp").forward(request, response);
            return;
        }

        String paymentMethod = paymentDAO.getPaymentMethod(ticketId);
        request.setAttribute("ticket", ticket);
        request.setAttribute("paymentMethod", paymentMethod);
        request.getRequestDispatcher("/passenger/ticket.jsp").forward(request, response);
    }
}