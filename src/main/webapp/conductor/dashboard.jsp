<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.bus.model.User" %>
<%
    User user = (User) session.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Conductor Dashboard</title>
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="../css/dashboard.css">
</head>
<body>
    <div class="dashboard">
        <div class="topbar">
            <h2>Conductor Dashboard</h2>
            <div>
                <span>Welcome, <%= user.getFullName() %></span>
                <a href="../LogoutServlet" class="logout-btn">Logout</a>
            </div>
        </div>

        <div class="menu">
            <a href="../AttendanceServlet" class="menu-card">Mark Attendance</a>
            <a href="../TimetableServlet" class="menu-card">View Timetable</a>
            <a href="../BusTimingServlet" class="menu-card">Add Arrival / Departure Time</a>
            <a href="../MaintenanceServlet" class="menu-card">Report Maintenance Issue</a>
        </div>
    </div>
</body>
</html>