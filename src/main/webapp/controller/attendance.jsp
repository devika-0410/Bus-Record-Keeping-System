<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.bus.model.*, java.util.List" %>
<%
    List<Attendance> attendanceList = (List<Attendance>) request.getAttribute("attendanceList");
%>
<!DOCTYPE html>
<html>
<head>
    <title>View Attendance</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/dashboard.css">
</head>
<body>
<div class="dashboard">
    <div class="topbar">
        <h2>All Staff Attendance</h2>
        <a href="controller/dashboard.jsp" class="logout-btn">Back to Dashboard</a>
    </div>

    <table border="1" cellpadding="8" style="width:100%; border-collapse:collapse; background:white;">
        <tr><th>Name</th><th>Bus</th><th>Date</th><th>Status</th></tr>
        <% for (Attendance a : attendanceList) { %>
        <tr>
            <td><%= a.getUserName() %></td>
            <td><%= a.getBusNumber() %></td>
            <td><%= a.getAttendanceDate() %></td>
            <td><%= a.getStatus() %></td>
        </tr>
        <% } %>
    </table>
</div>
</body>
</html>