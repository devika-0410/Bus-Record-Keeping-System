<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.bus.model.User" %>
<%
    User user = (User) session.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Controller Dashboard</title>
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="../css/dashboard.css">
</head>
<body>
    <div class="dashboard">
        <div class="topbar">
            <h2>Controller Dashboard</h2>
            <div>
                <span>Welcome, <%= user.getFullName() %></span>
                <a href="../LogoutServlet" class="logout-btn">Logout</a>
            </div>
        </div>

        <div class="menu">
            <a href="../TimetableServlet" class="menu-card">View / Create Timetable</a>
            <a href="../AttendanceServlet" class="menu-card">View Attendance</a>
            <a href="../BusTimingServlet" class="menu-card">Bus Arrivals / Departures / Delays</a>
            <a href="../AllTicketsServlet" class="menu-card">View Booked Tickets</a>
            <a href="../MaintenanceServlet" class="menu-card">Maintenance Reports</a>
        </div>
    </div>
</body>
</html>