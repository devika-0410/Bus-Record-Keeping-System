package com.bus.dao;

import com.bus.model.MaintenanceReport;
import com.bus.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaintenanceDAO {

    public boolean reportIssue(int busId, int reportedBy, String description) {
        String sql = "INSERT INTO maintenance_reports (bus_id, reported_by, issue_description, status) " +
                     "VALUES (?, ?, ?, 'PENDING')";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, busId);
            ps.setInt(2, reportedBy);
            ps.setString(3, description);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Controller: view all reports
    public List<MaintenanceReport> getAllReports() {
        List<MaintenanceReport> list = new ArrayList<>();
        String sql = "SELECT m.*, b.bus_number, u.full_name AS reported_by_name, " +
                     "u2.full_name AS updated_by_name " +
                     "FROM maintenance_reports m " +
                     "JOIN buses b ON m.bus_id = b.bus_id " +
                     "JOIN users u ON m.reported_by = u.user_id " +
                     "LEFT JOIN users u2 ON m.updated_by = u2.user_id " +
                     "ORDER BY m.reported_at DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Conductor/Driver: view only their own reports
    public List<MaintenanceReport> getReportsByUser(int userId) {
        List<MaintenanceReport> list = new ArrayList<>();
        String sql = "SELECT m.*, b.bus_number, u.full_name AS reported_by_name, " +
                     "u2.full_name AS updated_by_name " +
                     "FROM maintenance_reports m " +
                     "JOIN buses b ON m.bus_id = b.bus_id " +
                     "JOIN users u ON m.reported_by = u.user_id " +
                     "LEFT JOIN users u2 ON m.updated_by = u2.user_id " +
                     "WHERE m.reported_by = ? ORDER BY m.reported_at DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean updateStatus(int reportId, String status, int updatedBy) {
        String sql = "UPDATE maintenance_reports SET status = ?, updated_by = ?, " +
                     "resolved_at = ? WHERE report_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, updatedBy);
            if ("RESOLVED".equals(status)) {
                ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            } else {
                ps.setNull(3, Types.TIMESTAMP);
            }
            ps.setInt(4, reportId);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private MaintenanceReport mapRow(ResultSet rs) throws SQLException {
        MaintenanceReport m = new MaintenanceReport();
        m.setReportId(rs.getInt("report_id"));
        m.setBusId(rs.getInt("bus_id"));
        m.setBusNumber(rs.getString("bus_number"));
        m.setReportedBy(rs.getInt("reported_by"));
        m.setReportedByName(rs.getString("reported_by_name"));
        m.setIssueDescription(rs.getString("issue_description"));
        m.setStatus(rs.getString("status"));
        m.setReportedAt(rs.getTimestamp("reported_at"));
        m.setResolvedAt(rs.getTimestamp("resolved_at"));
        Object updatedByVal = rs.getObject("updated_by");
        m.setUpdatedBy(updatedByVal != null ? (Integer) updatedByVal : null);
        m.setUpdatedByName(rs.getString("updated_by_name"));
        return m;
    }
}