<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.bus.dao.BusDAO, com.bus.model.*, java.util.List" %>
<%
    List<MaintenanceReport> reports = (List<MaintenanceReport>) request.getAttribute("reports");
    List<Bus> buses = new BusDAO().getAllBuses();
%>
<!DOCTYPE html>
<html>
<head>
    <title>Maintenance Reports</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/dashboard.css">
</head>
<body>
<div class="dashboard">
    <div class="topbar">
        <h2>Report / View Maintenance Issues</h2>
        <a href="conductor/dashboard.jsp" class="logout-btn">Back to Dashboard</a>
    </div>

    <h3>Report a New Issue</h3>
    <form action="MaintenanceServlet" method="post">
        <input type="hidden" name="action" value="report">
        <label>Bus:</label>
        <select name="busId" required>
            <% for (Bus b : buses) { %>
                <option value="<%= b.getBusId() %>"><%= b.getBusNumber() %></option>
            <% } %>
        </select>

        <label>Issue Description:</label>
        <textarea name="issueDescription" rows="3" style="width:100%;" required></textarea>

        <button type="submit">Submit Report</button>
    </form>

    <h3>My Reported Issues</h3>
    <table border="1" cellpadding="8" style="width:100%; border-collapse:collapse; background:white;">
        <tr><th>Bus</th><th>Issue</th><th>Reported At</th><th>Status</th></tr>
        <% for (MaintenanceReport m : reports) { %>
        <tr>
            <td><%= m.getBusNumber() %></td>
            <td><%= m.getIssueDescription() %></td>
            <td><%= m.getReportedAt() %></td>
            <td><b><%= m.getStatus() %></b></td>
        </tr>
        <% } %>
    </table>
</div>
</body>
</html>