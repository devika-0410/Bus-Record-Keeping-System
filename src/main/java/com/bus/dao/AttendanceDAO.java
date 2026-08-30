package com.bus.dao;

import com.bus.model.Attendance;
import com.bus.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDAO {

    public boolean markAttendance(Attendance a) {
        String sql = "INSERT INTO attendance (user_id, bus_id, attendance_date, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, a.getUserId());
            ps.setInt(2, a.getBusId());
            ps.setDate(3, a.getAttendanceDate());
            ps.setString(4, a.getStatus());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // For Controller: view all attendance records
    public List<Attendance> getAllAttendance() {
        List<Attendance> list = new ArrayList<>();
        String sql = "SELECT a.*, u.full_name, b.bus_number FROM attendance a " +
                     "JOIN users u ON a.user_id = u.user_id " +
                     "JOIN buses b ON a.bus_id = b.bus_id " +
                     "ORDER BY a.attendance_date DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Attendance a = new Attendance();
                a.setAttendanceId(rs.getInt("attendance_id"));
                a.setUserId(rs.getInt("user_id"));
                a.setUserName(rs.getString("full_name"));
                a.setBusId(rs.getInt("bus_id"));
                a.setBusNumber(rs.getString("bus_number"));
                a.setAttendanceDate(rs.getDate("attendance_date"));
                a.setStatus(rs.getString("status"));
                list.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // For Conductor/Driver: view only their own attendance history
    public List<Attendance> getAttendanceByUser(int userId) {
        List<Attendance> list = new ArrayList<>();
        String sql = "SELECT a.*, u.full_name, b.bus_number FROM attendance a " +
                     "JOIN users u ON a.user_id = u.user_id " +
                     "JOIN buses b ON a.bus_id = b.bus_id " +
                     "WHERE a.user_id = ? ORDER BY a.attendance_date DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Attendance a = new Attendance();
                a.setAttendanceId(rs.getInt("attendance_id"));
                a.setUserId(rs.getInt("user_id"));
                a.setUserName(rs.getString("full_name"));
                a.setBusId(rs.getInt("bus_id"));
                a.setBusNumber(rs.getString("bus_number"));
                a.setAttendanceDate(rs.getDate("attendance_date"));
                a.setStatus(rs.getString("status"));
                list.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}