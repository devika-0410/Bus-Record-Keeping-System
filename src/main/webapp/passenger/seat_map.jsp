<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%
    Integer timetableId = (Integer) request.getAttribute("timetableId");
    Integer totalSeats = (Integer) request.getAttribute("totalSeats");
    List<Integer> bookedSeats = (List<Integer>) request.getAttribute("bookedSeats");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Select Seat</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/dashboard.css">
    <style>
        .bus-layout {
            max-width: 320px;
            margin: 20px auto;
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
        }
        .seat-grid {
            display: grid;
            grid-template-columns: 50px 50px 30px 50px 50px;
            gap: 10px;
            justify-content: center;
        }
        .seat {
            width: 50px;
            height: 50px;
            display: flex;
            align-items: center;
            justify-content: center;
            border-radius: 6px;
            font-weight: bold;
            cursor: pointer;
            border: none;
        }
        .available { background: #2ecc71; color: white; }
        .available:hover { background: #27ae60; }
        .booked { background: #e74c3c; color: white; cursor: not-allowed; }
        .aisle { visibility: hidden; }
        .legend { text-align: center; margin-top: 15px; }
        .legend span { display: inline-block; width: 15px; height: 15px; margin-right: 5px; vertical-align: middle; border-radius: 3px; }
    </style>
</head>
<body>
<div class="dashboard">
    <div class="topbar">
        <h2>Select Your Seat</h2>
        <a href="passenger/BusSearchServlet" class="logout-btn">Back to Search</a>
    </div>

    <div class="bus-layout">
        <h3 style="text-align:center;">🚌 Bus Layout</h3>
        <div class="seat-grid">
            <%
                int seatsPerRow = 4; // 2 left + 2 right
                for (int seat = 1; seat <= totalSeats; seat++) {
                    boolean isBooked = bookedSeats.contains(seat);
                    int posInRow = (seat - 1) % seatsPerRow;
            %>
                <% if (posInRow == 2) { %>
                    <div class="aisle"></div> <%-- gap between left and right seats --%>
                <% } %>
                <% if (isBooked) { %>
                    <button class="seat booked" disabled title="Already booked"><%= seat %></button>
                <% } else { %>
                    <form action="BookTicketServlet" method="post" style="margin:0;">
                        <input type="hidden" name="timetableId" value="<%= timetableId %>">
                        <input type="hidden" name="seatNumber" value="<%= seat %>">
                        <button type="submit" class="seat available" title="Click to book seat <%= seat %>"><%= seat %></button>
                    </form>
                <% } %>
            <% } %>
        </div>

        <div class="legend">
            <span style="background:#2ecc71;"></span> Available &nbsp;&nbsp;
            <span style="background:#e74c3c;"></span> Booked
        </div>
    </div>
</div>
</body>
</html>