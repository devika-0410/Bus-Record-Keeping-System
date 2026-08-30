<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.bus.model.Ticket" %>
<%
    Ticket ticket = (Ticket) request.getAttribute("ticket");
    String paymentMethod = (String) request.getAttribute("paymentMethod");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Your Ticket</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/dashboard.css">
    <style>
        .ticket-card {
            max-width: 420px;
            margin: 30px auto;
            background: white;
            border-radius: 10px;
            overflow: hidden;
            box-shadow: 0 3px 12px rgba(0,0,0,0.15);
        }
        .ticket-header {
            background: #2c3e50;
            color: white;
            padding: 15px 20px;
            text-align: center;
        }
        .ticket-body { padding: 20px; }
        .ticket-body p { margin: 8px 0; }
        .ticket-body .label { color: #7f8c8d; font-size: 13px; }
        .ticket-status {
            display: inline-block;
            padding: 4px 10px;
            border-radius: 4px;
            background: #2ecc71;
            color: white;
            font-size: 13px;
        }
        .dashed { border-top: 2px dashed #ccc; margin: 15px 0; }
    </style>
</head>
<body>
<div class="dashboard">
    <div class="topbar">
        <h2>Ticket Confirmed</h2>
        <a href="passenger/dashboard.jsp" class="logout-btn">Back to Dashboard</a>
    </div>

    <div class="ticket-card">
        <div class="ticket-header">
            <h3>🚌 Bus Ticket</h3>
        </div>
        <div class="ticket-body">
            <p class="label">Ticket ID</p>
            <p><b>#<%= ticket.getTicketId() %></b></p>

            <p class="label">Passenger</p>
            <p><%= ticket.getPassengerName() %></p>

            <div class="dashed"></div>

            <p class="label">Bus Number</p>
            <p><%= ticket.getBusNumber() %></p>

            <p class="label">Seat Number</p>
            <p><b><%= ticket.getSeatNumber() %></b></p>

            <p class="label">Travel Date</p>
            <p><%= ticket.getTravelDate() %></p>

            <div class="dashed"></div>

            <p class="label">Amount Paid</p>
            <p>₹<%= ticket.getAmount() %> (via <%= paymentMethod %>)</p>

            <p class="label">Status</p>
            <p><span class="ticket-status"><%= ticket.getStatus() %></span></p>

            <p style="color:#7f8c8d; font-size:12px; margin-top:15px;">
                This is a digitally generated ticket for a college mini-project demonstration. Not a real travel document.
            </p>
        </div>
    </div>
</div>
</body>
</html>