<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.bus.model.*, java.util.List" %>
<%
    List<Timetable> timetables = (List<Timetable>) request.getAttribute("timetables");
    List<BusTiming> timings = (List<BusTiming>) request.getAttribute("timings");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Bus Arrival / Departure</title>
    <link rel="stylesheet" href="scss/style.css">
    <link rel="stylesheet" href="css/dashboard.css">
</head>
<body>
<div class="dashboard">
    <div class="topbar">
        <h2>Bus Arrival / Departure Entry</h2>
        <a href="conductor/dashboard.jsp" class="logout-btn">Back to Dashboard</a>
    </div>

    <h3>Record Actual Arrival</h3>
    <form action="BusTimingServlet" method="post">
        <input type="hidden" name="type" value="arrival">
        <label>Select Trip (Timetable):</label>
        <select name="timetableId" required>
            <% for (Timetable t : timetables) { %>
                <option value="<%= t.getTimetableId() %>">
                    <%= t.getBusNumber() %> | <%= t.getSourcePlace() %> → <%= t.getDestinationPlace() %>
                    | <%= t.getTravelDate() %> | Sched. Arrival: <%= t.getScheduledArrival() %>
                </option>
            <% } %>
        </select>
        <label>Actual Arrival Time:</label>
        <input type="time" name="actualTime" required>
        <button type="submit">Record Arrival</button>
    </form>

    <h3>Record Actual Departure</h3>
    <form action="BusTimingServlet" method="post">
        <input type="hidden" name="type" value="departure">
        <label>Select Trip (Timetable):</label>
        <select name="timetableId" required>
            <% for (Timetable t : timetables) { %>
                <option value="<%= t.getTimetableId() %>">
                    <%= t.getBusNumber() %> | <%= t.getSourcePlace() %> → <%= t.getDestinationPlace() %>
                    | <%= t.getTravelDate() %> | Sched. Departure: <%= t.getScheduledDeparture() %>
                </option>
            <% } %>
        </select>
        <label>Actual Departure Time:</label>
        <input type="time" name="actualTime" required>
        <button type="submit">Record Departure</button>
    </form>

    <h3>All Recorded Timings & Delays</h3>
    <table border="1" cellpadding="8" style="width:100%; border-collapse:collapse; background:white;">
        <tr><th>Bus</th><th>Route</th><th>Sched. Arrival</th><th>Actual Arrival</th>
            <th>Sched. Departure</th><th>Actual Departure</th><th>Delay (min)</th></tr>
        <% for (BusTiming bt : timings) { %>
        <tr>
            <td><%= bt.getBusNumber() %></td>
            <td><%= bt.getSourcePlace() %> → <%= bt.getDestinationPlace() %></td>
            <td><%= bt.getScheduledArrival() %></td>
            <td><%= bt.getActualArrival() != null ? bt.getActualArrival() : "-" %></td>
            <td><%= bt.getScheduledDeparture() %></td>
            <td><%= bt.getActualDeparture() != null ? bt.getActualDeparture() : "-" %></td>
            <td><%= bt.getDelayMinutes() %></td>
        </tr>
        <% } %>
    </table>
</div>
</body>
</html>