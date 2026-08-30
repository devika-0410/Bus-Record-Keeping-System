<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.bus.model.*, java.util.List" %>
<%
    List<Route> routes = (List<Route>) request.getAttribute("routes");
    List<Timetable> results = (List<Timetable>) request.getAttribute("results");
    Boolean searched = (Boolean) request.getAttribute("searched");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Search Buses</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/dashboard.css">
</head>
<body>
<div class="dashboard">
    <div class="topbar">
        <h2>Search Available Buses</h2>
        <a href="passenger/dashboard.jsp" class="logout-btn">Back to Dashboard</a>
    </div>

    <form action="BusSearchServlet" method="get">
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

        <button type="submit">Search</button>
    </form>

    <% if (searched != null && searched) { %>
        <h3>Available Buses</h3>
        <% if (results == null || results.isEmpty()) { %>
            <p>No buses found for this route/date.</p>
        <% } else { %>
            <table border="1" cellpadding="8" style="width:100%; border-collapse:collapse; background:white;">
                <tr><th>Bus</th><th>Route</th><th>Departure</th><th>Arrival</th><th>Available Seats</th><th>Action</th></tr>
                <% for (Timetable t : results) { %>
                <tr>
                    <td><%= t.getBusNumber() %></td>
                    <td><%= t.getSourcePlace() %> → <%= t.getDestinationPlace() %></td>
                    <td><%= t.getScheduledDeparture() %></td>
                    <td><%= t.getScheduledArrival() %></td>
                    <td><%= t.getAvailableSeats() %> / <%= t.getTotalSeats() %></td>
                    <td>
                        <% if (t.getAvailableSeats() > 0) { %>
                            <a href="SeatMapServlet?timetableId=<%= t.getTimetableId() %>&totalSeats=<%= t.getTotalSeats() %>">
                                View Seats / Book
                            </a>
                        <% } else { %>
                            <span style="color:red;">Full</span>
                        <% } %>
                    </td>
                </tr>
                <% } %>
            </table>
        <% } %>
    <% } %>
</div>
</body>
</html>