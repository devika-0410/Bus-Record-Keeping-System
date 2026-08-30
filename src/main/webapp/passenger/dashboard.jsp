<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.bus.model.User" %>
<%
    User user = (User) session.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Passenger Dashboard</title>
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="../css/dashboard.css">
</head>
<body>
    <div class="dashboard">
        <div class="topbar">
            <h2>Passenger Dashboard</h2>
            <div>
                <span>Welcome, <%= user.getFullName() %></span>
                <a href="../LogoutServlet" class="logout-btn">Logout</a>
            </div>
        </div>

        <div class="menu">
            <a href="../BusSearchServlet" class="menu-card">Search Buses / Book Ticket</a>
            <a href="../MyTicketsServlet" class="menu-card">My Booked Tickets</a>
        </div>
    </div>
</body>
</html>