package com.bus.dao;

import com.bus.model.Timetable;
import com.bus.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TimetableDAO {

    public boolean createTimetable(Timetable t) {
        String sql = "INSERT INTO timetable (bus_id, route_id, travel_date, scheduled_arrival, scheduled_departure, created_by) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, t.getBusId());
            ps.setInt(2, t.getRouteId());
            ps.setDate(3, t.getTravelDate());
            ps.setTime(4, t.getScheduledArrival());
            ps.setTime(5, t.getScheduledDeparture());
            ps.setInt(6, t.getCreatedBy());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Timetable> getAllTimetables() {
        List<Timetable> list = new ArrayList<>();
        String sql = "SELECT t.*, b.bus_number, r.source_place, r.destination_place " +
                     "FROM timetable t " +
                     "JOIN buses b ON t.bus_id = b.bus_id " +
                     "JOIN routes r ON t.route_id = r.route_id " +
                     "ORDER BY t.travel_date, t.scheduled_departure";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Timetable t = new Timetable();
                t.setTimetableId(rs.getInt("timetable_id"));
                t.setBusId(rs.getInt("bus_id"));
                t.setBusNumber(rs.getString("bus_number"));
                t.setRouteId(rs.getInt("route_id"));
                t.setSourcePlace(rs.getString("source_place"));
                t.setDestinationPlace(rs.getString("destination_place"));
                t.setTravelDate(rs.getDate("travel_date"));
                t.setScheduledArrival(rs.getTime("scheduled_arrival"));
                t.setScheduledDeparture(rs.getTime("scheduled_departure"));
                t.setCreatedBy(rs.getInt("created_by"));
                list.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean updateTimetable(Timetable t) {
        String sql = "UPDATE timetable SET bus_id=?, route_id=?, travel_date=?, scheduled_arrival=?, scheduled_departure=? " +
                     "WHERE timetable_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, t.getBusId());
            ps.setInt(2, t.getRouteId());
            ps.setDate(3, t.getTravelDate());
            ps.setTime(4, t.getScheduledArrival());
            ps.setTime(5, t.getScheduledDeparture());
            ps.setInt(6, t.getTimetableId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Timetable> searchTimetables(int routeId, Date travelDate) {
        List<Timetable> list = new ArrayList<>();
        String sql = "SELECT t.*, b.bus_number, b.total_seats, r.source_place, r.destination_place " +
                     "FROM timetable t " +
                     "JOIN buses b ON t.bus_id = b.bus_id " +
                     "JOIN routes r ON t.route_id = r.route_id " +
                     "WHERE t.route_id = ? AND t.travel_date = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, routeId);
            ps.setDate(2, travelDate);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Timetable t = new Timetable();
                t.setTimetableId(rs.getInt("timetable_id"));
                t.setBusId(rs.getInt("bus_id"));
                t.setBusNumber(rs.getString("bus_number"));
                t.setTotalSeats(rs.getInt("total_seats"));
                t.setRouteId(rs.getInt("route_id"));
                t.setSourcePlace(rs.getString("source_place"));
                t.setDestinationPlace(rs.getString("destination_place"));
                t.setTravelDate(rs.getDate("travel_date"));
                t.setScheduledArrival(rs.getTime("scheduled_arrival"));
                t.setScheduledDeparture(rs.getTime("scheduled_departure"));
                list.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public Timetable getTimetableById(int timetableId) {
        String sql = "SELECT t.*, b.bus_number, b.total_seats, r.source_place, r.destination_place " +
                     "FROM timetable t " +
                     "JOIN buses b ON t.bus_id = b.bus_id " +
                     "JOIN routes r ON t.route_id = r.route_id " +
                     "WHERE t.timetable_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, timetableId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Timetable t = new Timetable();
                t.setTimetableId(rs.getInt("timetable_id"));
                t.setBusId(rs.getInt("bus_id"));
                t.setBusNumber(rs.getString("bus_number"));
                t.setTotalSeats(rs.getInt("total_seats"));
                t.setRouteId(rs.getInt("route_id"));
                t.setSourcePlace(rs.getString("source_place"));
                t.setDestinationPlace(rs.getString("destination_place"));
                t.setTravelDate(rs.getDate("travel_date"));
                t.setScheduledArrival(rs.getTime("scheduled_arrival"));
                t.setScheduledDeparture(rs.getTime("scheduled_departure"));
                return t;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}