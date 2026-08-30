<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.bus.model.*, java.util.List" %>
<%
    List<Timetable> timetables = (List<Timetable>) request.getAttribute("timetables");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Timetable</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/dashboard.css">
</head>
<body>
<div class="dashboard">
    <div class="topbar">
        <h2>Bus Timetable</h2>
        <a href="driver/dashboard.jsp" class="logout-btn">Back to Dashboard</a>
    </div>

    <table border="1" cellpadding="8" style="width:100%; border-collapse:collapse; background:white;">
        <tr>
            <th>ID</th><th>Bus</th><th>Route</th><th>Date</th>
            <th>Sched. Arrival</th><th>Sched. Departure</th>
        </tr>
        <% for (Timetable t : timetables) { %>
        <tr>
            <td><%= t.getTimetableId() %></td>
            <td><%= t.getBusNumber() %></td>
            <td><%= t.getSourcePlace() %> → <%= t.getDestinationPlace() %></td>
            <td><%= t.getTravelDate() %></td>
            <td><%= t.getScheduledArrival() %></td>
            <td><%= t.getScheduledDeparture() %></td>
        </tr>
        <% } %>
    </table>
</div>
</body>
</html>