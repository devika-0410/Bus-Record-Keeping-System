package com.bus.dao;

import com.bus.util.DBConnection;
import java.sql.*;

public class PaymentDAO {

    public boolean recordPayment(int ticketId, String method, double amount) {
        String sql = "INSERT INTO payments (ticket_id, payment_method, amount, payment_status) " +
                     "VALUES (?, ?, ?, 'SUCCESS')";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, ticketId);
            ps.setString(2, method);
            ps.setDouble(3, amount);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isAlreadyPaid(int ticketId) {
        String sql = "SELECT payment_id FROM payments WHERE ticket_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, ticketId);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public String getPaymentMethod(int ticketId) {
        String sql = "SELECT payment_method FROM payments WHERE ticket_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, ticketId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getString("payment_method");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "N/A";
    }
}