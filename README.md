# 🚌 Bus Record Keeping System

A role-based web application for managing bus operations — timetables, attendance, real-time delay tracking, ticket booking with visual seat selection, and maintenance reporting.

Built as a college mini-project using **Java Servlets, JSP, and MySQL**.

## Features

### Four User Roles
- **Controller** — creates/manages timetables, views attendance and bus delays, oversees all ticket bookings, resolves maintenance issues
- **Conductor / Driver** — mark attendance, record actual arrival/departure times, report maintenance issues
- **Passenger** — search buses by route & date, book seats via a visual seat map, make a (simulated) payment, view tickets

### Key Functionality
- Role-based authentication with session management and route protection (via a servlet Filter)
- **BCrypt password hashing** — passwords are never stored in plain text
- Ticket-ownership access control — passengers can only view their own tickets
- Automatic bus delay calculation (scheduled vs. actual time)
- Real-time seat availability with a visual, color-coded seat map
- Seat-booking double-check to guard against double-booking
- Simulated payment flow (UPI / Card / Cash) for demonstration purposes

## Tech Stack

| Layer | Technology |
|---|---|
| Frontend | HTML, CSS, JavaScript, JSP |
| Backend | Java Servlets |
| Database | MySQL |
| Security | BCrypt (jBCrypt) for password hashing |
| Server | Apache Tomcat 10 |

## Project ArchitectureBrowser (HTML/CSS/JS, JSP) → Servlet (Controller layer) → DAO classes (JDBC) → MySQL Database


## Folder Structure

src/main/java/com/bus/
├── model/ - Plain data classes (User, Bus, Ticket, etc.)
├── dao/ - JDBC/SQL logic, one class per entity
├── servlet/ - Request handling (Login, BookTicket, Payment, etc.)
├── filter/ - SessionFilter for route protection
└── util/ - DBConnection

src/main/webapp/
├── controller/ / conductor/ / driver/ / passenger/ - role-specific JSPs
├── css/ - stylesheets
└── WEB-INF/ - web.xml, lib/


## Setup Instructions

### Prerequisites
- JDK 17+
- Apache Tomcat 9 or 10
- MySQL Server
- Eclipse IDE (or any Java EE IDE)

### Dependencies (add these jars to `WEB-INF/lib`)
- `mysql-connector-j` (8.x/9.x or later)
- `jbcrypt-0.4.jar`

### Database Setup
1. Run `database_setup.sql` in MySQL Workbench to create the schema.
2. Open `com/bus/util/DBConnection.java` and update:
```java
   private static final String USERNAME = "root";
   private static final String PASSWORD = "YOUR_MYSQL_PASSWORD_HERE";
```

### Running the Project
1. Import into Eclipse as a Dynamic Web Project.
2. Add the required jars to `WEB-INF/lib` and to Build Path.
3. Deploy to Tomcat and run.
4. Visit `http://localhost:8080/BusRecordKeepingSystem/index.jsp`

## Database Schema

9 tables: `users`, `routes`, `buses`, `timetable`, `attendance`, `bus_timing`, `tickets`, `payments`, `maintenance_reports`. See `database_setup.sql` for full definitions.

## Security Notes

- Passwords are hashed using BCrypt before storage — never stored or compared in plain text.
- Ticket viewing is access-controlled — a passenger cannot view another passenger's ticket by guessing/changing the ticket ID.
- All SQL queries use `PreparedStatement` to prevent SQL injection.

## Possible Future Improvements

- Connection pooling (HikariCP) for better performance under concurrent load
- Database-level unique constraint on `(timetable_id, seat_number)` to eliminate any remaining race condition on seat booking
- Real payment gateway integration
- Externalized configuration (`.properties` file) instead of hardcoded DB credentials

## Author

Built as a college mini-project.