<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.bus.model.Ticket, java.util.List" %>
<%
    List<Ticket> tickets = (List<Ticket>) request.getAttribute("tickets");
%>
<!DOCTYPE html>
<html>
<head>
    <title>All Booked Tickets</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/dashboard.css">
</head>
<body>
<div class="dashboard">
    <div class="topbar">
        <h2>All Passenger Tickets</h2>
        <a href="controller/dashboard.jsp" class="logout-btn">Back to Dashboard</a>
    </div>

    <% if (tickets.isEmpty()) { %>
        <p>No tickets booked yet.</p>
    <% } else { %>
        <table border="1" cellpadding="8" style="width:100%; border-collapse:collapse; background:white;">
            <tr><th>Ticket ID</th><th>Passenger</th><th>Bus</th><th>Seat</th><th>Travel Date</th><th>Amount</th><th>Status</th><th>Booked At</th></tr>
            <% for (Ticket t : tickets) { %>
            <tr>
                <td>#<%= t.getTicketId() %></td>
                <td><%= t.getPassengerName() %></td>
                <td><%= t.getBusNumber() %></td>
                <td><%= t.getSeatNumber() %></td>
                <td><%= t.getTravelDate() %></td>
                <td>₹<%= t.getAmount() %></td>
                <td><%= t.getStatus() %></td>
                <td><%= t.getBookedAt() %></td>
            </tr>
            <% } %>
        </table>
    <% } %>
</div>
</body>
</html>