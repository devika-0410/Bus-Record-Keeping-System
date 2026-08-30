<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.bus.model.Ticket" %>
<%
    Ticket ticket = (Ticket) request.getAttribute("ticket");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Payment</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/dashboard.css">
</head>
<body>
<div class="dashboard">
    <div class="topbar">
        <h2>Complete Payment</h2>
        <a href="passenger/dashboard.jsp" class="logout-btn">Back to Dashboard</a>
    </div>

    <% if (request.getAttribute("error") != null) { %>
        <p style="color:red;"><%= request.getAttribute("error") %></p>
    <% } %>

    <div style="background:white; padding:20px; border-radius:8px; max-width:400px; margin:0 auto;">
        <h3>Booking Summary</h3>
        <p><b>Bus:</b> <%= ticket.getBusNumber() %></p>
        <p><b>Seat Number:</b> <%= ticket.getSeatNumber() %></p>
        <p><b>Travel Date:</b> <%= ticket.getTravelDate() %></p>
        <p><b>Amount to Pay:</b> ₹<%= ticket.getAmount() %></p>

        <p style="color:#7f8c8d; font-size:13px;">
            ⚠️ This is a simulated payment system for demonstration purposes only. No real transaction will occur.
        </p>

        <form action="PaymentServlet" method="post">
            <input type="hidden" name="ticketId" value="<%= ticket.getTicketId() %>">

            <label>Select Payment Method:</label>
            <select name="paymentMethod" required>
                <option value="UPI">UPI</option>
                <option value="CARD">Card</option>
                <option value="CASH">Cash / Other</option>
            </select>

            <button type="submit">Pay ₹<%= ticket.getAmount() %> (Simulated)</button>
        </form>
    </div>
</div>
</body>
</html>