<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.bus.dao.BusDAO, com.bus.dao.RouteDAO, com.bus.model.*, java.util.List" %>
<%
    List<Timetable> timetables = (List<Timetable>) request.getAttribute("timetables");
    List<Bus> buses = new BusDAO().getAllBuses();
    List<Route> routes = new RouteDAO().getAllRoutes();
%>
<!DOCTYPE html>
<html>
<head>
    <title>Timetable Management</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/dashboard.css">
</head>
<body>
<div class="dashboard">
    <div class="topbar">
        <h2>Timetable Management</h2>
        <div>
            <a href="controller/dashboard.jsp" class="logout-btn">Back to Dashboard</a>
        </div>
    </div>

    <h3>Create New Timetable Entry</h3>
    <form action="TimetableServlet" method="post">
        <input type="hidden" name="action" value="create">

        <label>Bus:</label>
        <select name="busId" required>
            <% for (Bus b : buses) { %>
                <option value="<%= b.getBusId() %>">
                    <%= b.getBusNumber() %> (<%= b.getSourcePlace() %> → <%= b.getDestinationPlace() %>)
                </option>
            <% } %>
        </select>

        <label>Route:</label>
        <select name="routeId" required>
            <% for (Route r : routes) { %>
                <option value="<%= r.getRouteId() %>">
                    <%= r.getSourcePlace() %> → <%= r.getDestinationPlace() %>
                </option>
            <% } %>
        </select>

        <label>Travel Date:</label>
        <input type="date" name="travelDate" required>

        <label>Scheduled Arrival:</label>
        <input type="time" name="scheduledArrival" required>

        <label>Scheduled Departure:</label>
        <input type="time" name="scheduledDeparture" required>

        <button type="submit">Create Timetable</button>
    </form>

    <h3>All Timetables</h3>
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