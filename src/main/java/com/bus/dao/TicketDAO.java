package com.bus.dao;

import com.bus.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {

    // Returns list of seat numbers already booked for a given timetable (trip)
    public List<Integer> getBookedSeatNumbers(int timetableId) {
        List<Integer> seats = new ArrayList<>();
        String sql = "SELECT seat_number FROM tickets WHERE timetable_id = ? AND status = 'BOOKED'";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, timetableId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                seats.add(rs.getInt("seat_number"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seats;
    }

    // Count of booked seats for a timetable (used in search results)
    public int countBookedSeats(int timetableId) {
        String sql = "SELECT COUNT(*) FROM tickets WHERE timetable_id = ? AND status = 'BOOKED'";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, timetableId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    // Check if a specific seat is already booked (used right before booking, to prevent double-booking)
    public boolean isSeatBooked(int timetableId, int seatNumber) {
        String sql = "SELECT ticket_id FROM tickets WHERE timetable_id = ? AND seat_number = ? AND status = 'BOOKED'";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, timetableId);
            ps.setInt(2, seatNumber);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // true if a row exists = already booked
        } catch (SQLException e) {
            e.printStackTrace();
            return true; // fail-safe: if we can't check, assume booked to avoid double-booking
        }
    }

    // Insert a new ticket (this reserves the seat)
    public int bookTicket(int passengerId, int busId, int timetableId, int seatNumber,
                           java.sql.Date travelDate, double amount) {
        String sql = "INSERT INTO tickets (passenger_id, bus_id, timetable_id, seat_number, travel_date, amount, status) " +
                     "VALUES (?, ?, ?, ?, ?, ?, 'BOOKED')";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, passengerId);
            ps.setInt(2, busId);
            ps.setInt(3, timetableId);
            ps.setInt(4, seatNumber);
            ps.setDate(5, travelDate);
            ps.setDouble(6, amount);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                ResultSet keys = ps.getGeneratedKeys();
                if (keys.next()) {
                    return keys.getInt(1); // return the new ticket_id
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // failure
    }
    
    public com.bus.model.Ticket getTicketById(int ticketId) {
        String sql = "SELECT t.*, b.bus_number, u.full_name AS passenger_name " +
                     "FROM tickets t " +
                     "JOIN buses b ON t.bus_id = b.bus_id " +
                     "JOIN users u ON t.passenger_id = u.user_id " +
                     "WHERE t.ticket_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, ticketId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                com.bus.model.Ticket t = new com.bus.model.Ticket();
                t.setTicketId(rs.getInt("ticket_id"));
                t.setPassengerId(rs.getInt("passenger_id"));
                t.setPassengerName(rs.getString("passenger_name"));
                t.setBusId(rs.getInt("bus_id"));
                t.setBusNumber(rs.getString("bus_number"));
                t.setTimetableId(rs.getInt("timetable_id"));
                t.setSeatNumber(rs.getInt("seat_number"));
                t.setTravelDate(rs.getDate("travel_date"));
                t.setAmount(rs.getDouble("amount"));
                t.setStatus(rs.getString("status"));
                t.setBookedAt(rs.getTimestamp("booked_at"));
                return t;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<com.bus.model.Ticket> getTicketsByPassenger(int passengerId) {
        List<com.bus.model.Ticket> list = new ArrayList<>();
        String sql = "SELECT t.*, b.bus_number, u.full_name AS passenger_name " +
                     "FROM tickets t " +
                     "JOIN buses b ON t.bus_id = b.bus_id " +
                     "JOIN users u ON t.passenger_id = u.user_id " +
                     "WHERE t.passenger_id = ? " +
                     "ORDER BY t.booked_at DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, passengerId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                com.bus.model.Ticket t = new com.bus.model.Ticket();
                t.setTicketId(rs.getInt("ticket_id"));
                t.setPassengerId(rs.getInt("passenger_id"));
                t.setPassengerName(rs.getString("passenger_name"));
                t.setBusId(rs.getInt("bus_id"));
                t.setBusNumber(rs.getString("bus_number"));
                t.setTimetableId(rs.getInt("timetable_id"));
                t.setSeatNumber(rs.getInt("seat_number"));
                t.setTravelDate(rs.getDate("travel_date"));
                t.setAmount(rs.getDouble("amount"));
                t.setStatus(rs.getString("status"));
                t.setBookedAt(rs.getTimestamp("booked_at"));
                list.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<com.bus.model.Ticket> getAllTickets() {
        List<com.bus.model.Ticket> list = new ArrayList<>();
        String sql = "SELECT t.*, b.bus_number, u.full_name AS passenger_name " +
                     "FROM tickets t " +
                     "JOIN buses b ON t.bus_id = b.bus_id " +
                     "JOIN users u ON t.passenger_id = u.user_id " +
                     "ORDER BY t.booked_at DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                com.bus.model.Ticket t = new com.bus.model.Ticket();
                t.setTicketId(rs.getInt("ticket_id"));
                t.setPassengerId(rs.getInt("passenger_id"));
                t.setPassengerName(rs.getString("passenger_name"));
                t.setBusId(rs.getInt("bus_id"));
                t.setBusNumber(rs.getString("bus_number"));
                t.setTimetableId(rs.getInt("timetable_id"));
                t.setSeatNumber(rs.getInt("seat_number"));
                t.setTravelDate(rs.getDate("travel_date"));
                t.setAmount(rs.getDouble("amount"));
                t.setStatus(rs.getString("status"));
                t.setBookedAt(rs.getTimestamp("booked_at"));
                list.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}