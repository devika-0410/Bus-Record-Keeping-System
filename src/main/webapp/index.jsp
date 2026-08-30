<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Bus Record Keeping System</title>
    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; }
        body {
            font-family: 'Segoe UI', Arial, sans-serif;
            background: #eef1f5;
            color: #2c3e50;
        }
        .navbar {
            background: #1a3c6e;
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 16px 40px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.12);
        }
        .navbar .brand {
            color: white;
            font-size: 19px;
            font-weight: 700;
            letter-spacing: 0.3px;
        }
        .nav-links {
            display: flex;
            gap: 8px;
        }
        .nav-links button, .nav-links a {
            background: none;
            border: none;
            color: white;
            font-size: 14px;
            font-weight: 600;
            padding: 9px 18px;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
            transition: background 0.2s;
        }
        .nav-links button:hover, .nav-links a:hover {
            background: rgba(255,255,255,0.15);
        }
        .nav-links button.active {
            background: rgba(255,255,255,0.22);
        }
        .nav-links a.btn-outline {
            border: 1.5px solid rgba(255,255,255,0.6);
        }
        .nav-links a.btn-filled {
            background: white;
            color: #1a3c6e;
        }
        .nav-links a.btn-filled:hover {
            background: #e8edf3;
        }

        .page-section {
            display: none;
            max-width: 950px;
            margin: 0 auto;
            padding: 60px 30px;
        }
        .page-section.active { display: block; }

        .hero {
            text-align: center;
            margin-bottom: 40px;
        }
        .hero h1 {
            font-size: 34px;
            color: #1a3c6e;
            margin-bottom: 12px;
        }
        .hero p {
            font-size: 16px;
            color: #5a6472;
            max-width: 600px;
            margin: 0 auto 28px;
        }
        .hero .cta-buttons a {
            display: inline-block;
            padding: 12px 28px;
            margin: 0 8px;
            border-radius: 6px;
            font-weight: 600;
            text-decoration: none;
            font-size: 14px;
        }
        .cta-buttons a.primary {
            background: #1a3c6e;
            color: white;
        }
        .cta-buttons a.primary:hover { background: #14305a; }
        .cta-buttons a.secondary {
            background: white;
            color: #1a3c6e;
            border: 1.5px solid #1a3c6e;
        }
        .cta-buttons a.secondary:hover { background: #f0f4f8; }

        .feature-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
            gap: 20px;
            margin-top: 40px;
        }
        .feature-card {
            background: white;
            padding: 24px 20px;
            border-radius: 8px;
            box-shadow: 0 1px 4px rgba(0,0,0,0.08);
            border-top: 3px solid #1a3c6e;
            text-align: center;
        }
        .feature-card h3 {
            color: #1a3c6e;
            font-size: 16px;
            margin-bottom: 8px;
        }
        .feature-card p {
            font-size: 13.5px;
            color: #6b7785;
        }

        .about-card {
            background: white;
            padding: 32px;
            border-radius: 8px;
            box-shadow: 0 1px 4px rgba(0,0,0,0.08);
        }
        .about-card h2 {
            color: #1a3c6e;
            margin-bottom: 16px;
        }
        .about-card p {
            font-size: 14.5px;
            line-height: 1.7;
            color: #3d4852;
            margin-bottom: 14px;
        }
        .role-list {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
            gap: 14px;
            margin-top: 20px;
        }
        .role-item {
            background: #f5f7fa;
            padding: 14px 16px;
            border-radius: 6px;
            border-left: 3px solid #1a3c6e;
        }
        .role-item b { color: #1a3c6e; }
    </style>
</head>
<body>

    <div class="navbar">
        <div class="brand">🚌 Bus Record Keeping System</div>
        <div class="nav-links">
            <button id="homeTab" class="active" onclick="showTab('home')">Home</button>
            <button id="aboutTab" onclick="showTab('about')">About</button>
            <a href="register.jsp" class="btn-outline">Register</a>
            <a href="login.jsp" class="btn-filled">Login</a>
        </div>
    </div>

    <!-- HOME SECTION -->
    <div id="home" class="page-section active">
        <div class="hero">
            <h1>Manage Your Bus Operations, End to End</h1>
            <p>A simple system for controllers, conductors, drivers, and passengers to manage
               timetables, attendance, bus timings, tickets, and maintenance — all in one place.</p>
            <div class="cta-buttons">
                <a href="register.jsp" class="primary">Get Started</a>
                <a href="login.jsp" class="secondary">Login</a>
            </div>
        </div>

        <div class="feature-grid">
            <div class="feature-card">
                <h3>📅 Timetable Management</h3>
                <p>Controllers create and manage bus schedules across routes.</p>
            </div>
            <div class="feature-card">
                <h3>🕒 Live Delay Tracking</h3>
                <p>Automatic delay calculation from scheduled vs actual timings.</p>
            </div>
            <div class="feature-card">
                <h3>🎫 Easy Ticket Booking</h3>
                <p>Passengers pick a seat visually and book instantly.</p>
            </div>
            <div class="feature-card">
                <h3>🛠️ Maintenance Reports</h3>
                <p>Conductors/drivers report issues; controllers track resolution.</p>
            </div>
        </div>
    </div>

    <!-- ABOUT SECTION -->
    <div id="about" class="page-section">
        <div class="about-card">
            <h2>About This Project</h2>
            <p>
                The <b>Bus Record Keeping System</b> is a web-based application built to digitize
                and simplify daily bus operations — covering scheduling, attendance, real-time
                delay tracking, ticket booking, and maintenance reporting.
            </p>
            <p>
                The system supports four types of users, each with a dedicated dashboard and
                role-specific permissions:
            </p>
            <div class="role-list">
                <div class="role-item"><b>Controller</b><br>Manages timetables, monitors attendance,
                    tracks delays, views bookings, and resolves maintenance issues.</div>
                <div class="role-item"><b>Conductor</b><br>Marks attendance, records bus timings,
                    and reports maintenance issues.</div>
                <div class="role-item"><b>Driver</b><br>Marks attendance, records bus timings,
                    and reports maintenance issues.</div>
                <div class="role-item"><b>Passenger</b><br>Searches buses, books seats visually,
                    makes payment, and views tickets.</div>
            </div>
            <p style="margin-top:20px;">
                Built with Java Servlets, JSP, and MySQL, this project demonstrates a complete
                role-based web application with real-time data handling and session-based authentication.
            </p>
        </div>
    </div>

    <script>
        function showTab(tab) {
            document.getElementById('home').classList.remove('active');
            document.getElementById('about').classList.remove('active');
            document.getElementById('homeTab').classList.remove('active');
            document.getElementById('aboutTab').classList.remove('active');

            document.getElementById(tab).classList.add('active');
            document.getElementById(tab + 'Tab').classList.add('active');
        }
    </script>

</body>
</html>