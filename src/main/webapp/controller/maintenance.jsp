<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.bus.model.*, java.util.List" %>
<%
    List<MaintenanceReport> reports = (List<MaintenanceReport>) request.getAttribute("reports");
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
        <h2>All Maintenance Reports</h2>
        <a href="controller/dashboard.jsp" class="logout-btn">Back to Dashboard</a>
    </div>

    <table border="1" cellpadding="8" style="width:100%; border-collapse:collapse; background:white;">
        <tr><th>Bus</th><th>Issue</th><th>Reported By</th><th>Reported At</th><th>Status</th><th>Update</th></tr>
        <% for (MaintenanceReport m : reports) { %>
        <tr>
            <td><%= m.getBusNumber() %></td>
            <td><%= m.getIssueDescription() %></td>
            <td><%= m.getReportedByName() %></td>
            <td><%= m.getReportedAt() %></td>
            <td><b><%= m.getStatus() %></b></td>
            <td>
                <form action="MaintenanceServlet" method="post" style="margin:0;">
                    <input type="hidden" name="action" value="updateStatus">
                    <input type="hidden" name="reportId" value="<%= m.getReportId() %>">
                    <% if ("PENDING".equals(m.getStatus())) { %>
                        <input type="hidden" name="status" value="RESOLVED">
                        <button type="submit">Mark Resolved</button>
                    <% } else { %>
                        <input type="hidden" name="status" value="PENDING">
                        <button type="submit">Mark Pending</button>
                    <% } %>
                </form>
            </td>
        </tr>
        <% } %>
    </table>
</div>
</body>
</html>