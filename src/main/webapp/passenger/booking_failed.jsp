<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Booking Failed</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/dashboard.css">
</head>
<body>
<div class="dashboard">
    <div class="topbar">
        <h2>Booking Unsuccessful</h2>
        <a href="dashboard.jsp" class="logout-btn">Back to Dashboard</a>
    </div>
    <p style="color:red; font-size:16px;"><%= request.getAttribute("error") %></p>
    <a href="passenger/BusSearchServlet">← Search Again</a>
</div>
</body>
</html>