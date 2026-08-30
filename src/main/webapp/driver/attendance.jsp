<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.bus.dao.BusDAO, com.bus.model.*, java.util.List" %>
<%
    List<Attendance> attendanceList = (List<Attendance>) request.getAttribute("attendanceList");
    List<Bus> buses = new BusDAO().getAllBuses();
%>
<!DOCTYPE html>
<html>
<head>
    <title>Mark Attendance</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/dashboard.css">
</head>
<body>
<div class="dashboard">
    <div class="topbar">
        <h2>Mark Attendance</h2>
        <a href="driver/dashboard.jsp" class="logout-btn">Back to Dashboard</a>
    </div>

    <h3>Mark Today's Attendance</h3>
    <form action="AttendanceServlet" method="post">
        <label>Bus:</label>
        <select name="busId" required>
            <% for (Bus b : buses) { %>
                <option value="<%= b.getBusId() %>"><%= b.getBusNumber() %></option>
            <% } %>
        </select>

        <label>Date:</label>
        <input type="date" name="attendanceDate" required>

        <button type="submit">Mark Present</button>
    </form>

    <h3>My Attendance History</h3>
    <table border="1" cellpadding="8" style="width:100%; border-collapse:collapse; background:white;">
        <tr><th>Date</th><th>Bus</th><th>Status</th></tr>
        <% for (Attendance a : attendanceList) { %>
        <tr>
            <td><%= a.getAttendanceDate() %></td>
            <td><%= a.getBusNumber() %></td>
            <td><%= a.getStatus() %></td>
        </tr>
        <% } %>
    </table>
</div>
</body>
</html>