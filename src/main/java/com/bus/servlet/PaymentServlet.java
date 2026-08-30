package com.bus.servlet;

import com.bus.dao.PaymentDAO;
import com.bus.dao.TicketDAO;
import com.bus.model.Ticket;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet {

    private TicketDAO ticketDAO = new TicketDAO();
    private PaymentDAO paymentDAO = new PaymentDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int ticketId = Integer.parseInt(request.getParameter("ticketId"));
        Ticket ticket = ticketDAO.getTicketById(ticketId);

        if (ticket == null) {
            response.sendRedirect("BusSearchServlet");
            return;
        }

        // If already paid, skip straight to ticket view
        if (paymentDAO.isAlreadyPaid(ticketId)) {
            response.sendRedirect("TicketViewServlet?ticketId=" + ticketId);
            return;
        }

        request.setAttribute("ticket", ticket);
        request.getRequestDispatcher("/passenger/payment.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int ticketId = Integer.parseInt(request.getParameter("ticketId"));
        String method = request.getParameter("paymentMethod");

        Ticket ticket = ticketDAO.getTicketById(ticketId);
        if (ticket == null) {
            response.sendRedirect("BusSearchServlet");
            return;
        }

        boolean success = paymentDAO.recordPayment(ticketId, method, ticket.getAmount());

        if (success) {
            // Redirect to ticket view (Step 12 builds this fully)
            response.sendRedirect("TicketViewServlet?ticketId=" + ticketId);
        } else {
            request.setAttribute("error", "Payment failed. Please try again.");
            request.setAttribute("ticket", ticket);
            request.getRequestDispatcher("/passenger/payment.jsp").forward(request, response);
        }
    }
}