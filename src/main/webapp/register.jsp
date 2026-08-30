<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register - Bus Record System</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="form-container">
        <h2>Register</h2>

        <% if (request.getAttribute("error") != null) { %>
            <p class="error"><%= request.getAttribute("error") %></p>
        <% } %>

        <form action="RegisterServlet" method="post">
            <label>Full Name:</label>
            <input type="text" name="fullName" required>

            <label>Email:</label>
            <input type="email" name="email" required>

            <label>Password:</label>
            <input type="password" name="password" required>

            <label>Phone:</label>
            <input type="text" name="phone" required>

            <label>Role:</label>
            <select name="role" required>
                <option value="CONTROLLER">Controller</option>
                <option value="CONDUCTOR">Conductor</option>
                <option value="DRIVER">Driver</option>
                <option value="PASSENGER">Passenger</option>
            </select>

            <button type="submit">Register</button>
        </form>

        <p>Already have an account? <a href="login.jsp">Login here</a></p>
    </div>
</body>
</html>