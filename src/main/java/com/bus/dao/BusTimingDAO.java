package com.bus.dao;

import com.bus.model.BusTiming;
import com.bus.util.DBConnection;
import java.sql.*;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class BusTimingDAO {

    // Calculate delay in minutes between scheduled and actual time
    private int calculateDelay(Time scheduled, Time actual) {
        LocalTime schedLT = scheduled.toLocalTime();
        LocalTime actualLT = actual.toLocalTime();
        long diff = ChronoUnit.MINUTES.between(schedLT, actualLT);
        return (int) Math.max(diff, 0); // if early/on-time, show 0 delay
    }

    public boolean recordArrival(int timetableId, Time actualArrival, int recordedBy) {
        // First get the scheduled arrival time to calculate delay
        String getSchedSql = "SELECT scheduled_arrival FROM timetable WHERE timetable_id = ?";
        String insertSql = "INSERT INTO bus_timing (timetable_id, actual_arrival, delay_minutes, recorded_by) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection()) {
            Time scheduledArrival = null;
            try (PreparedStatement ps1 = conn.prepareStatement(getSchedSql)) {
                ps1.setInt(1, timetableId);
                ResultSet rs = ps1.executeQuery();
                if (rs.next()) {
                    scheduledArrival = rs.getTime("scheduled_arrival");
                }
            }

            int delay = (scheduledArrival != null) ? calculateDelay(scheduledArrival, actualArrival) : 0;

            try (PreparedStatement ps2 = conn.prepareStatement(insertSql)) {
                ps2.setInt(1, timetableId);
                ps2.setTime(2, actualArrival);
                ps2.setInt(3, delay);
                ps2.setInt(4, recordedBy);
                return ps2.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean recordDeparture(int timetableId, Time actualDeparture, int recordedBy) {
        String getSchedSql = "SELECT scheduled_departure FROM timetable WHERE timetable_id = ?";
        String insertSql = "INSERT INTO bus_timing (timetable_id, actual_departure, delay_minutes, recorded_by) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection()) {
            Time scheduledDeparture = null;
            try (PreparedStatement ps1 = conn.prepareStatement(getSchedSql)) {
                ps1.setInt(1, timetableId);
                ResultSet rs = ps1.executeQuery();
                if (rs.next()) {
                    scheduledDeparture = rs.getTime("scheduled_departure");
                }
            }

            int delay = (scheduledDeparture != null) ? calculateDelay(scheduledDeparture, actualDeparture) : 0;

            try (PreparedStatement ps2 = conn.prepareStatement(insertSql)) {
                ps2.setInt(1, timetableId);
                ps2.setTime(2, actualDeparture);
                ps2.setInt(3, delay);
                ps2.setInt(4, recordedBy);
                return ps2.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // For Controller/Conductor/Driver: view all recorded timings with delay
    public List<BusTiming> getAllTimings() {
        List<BusTiming> list = new ArrayList<>();
        String sql = "SELECT bt.*, t.scheduled_arrival, t.scheduled_departure, b.bus_number, r.source_place, r.destination_place " +
                     "FROM bus_timing bt " +
                     "JOIN timetable t ON bt.timetable_id = t.timetable_id " +
                     "JOIN buses b ON t.bus_id = b.bus_id " +
                     "JOIN routes r ON t.route_id = r.route_id " +
                     "ORDER BY bt.recorded_at DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                BusTiming bt = new BusTiming();
                bt.setTimingId(rs.getInt("timing_id"));
                bt.setTimetableId(rs.getInt("timetable_id"));
                bt.setBusNumber(rs.getString("bus_number"));
                bt.setSourcePlace(rs.getString("source_place"));
                bt.setDestinationPlace(rs.getString("destination_place"));
                bt.setScheduledArrival(rs.getTime("scheduled_arrival"));
                bt.setScheduledDeparture(rs.getTime("scheduled_departure"));
                bt.setActualArrival(rs.getTime("actual_arrival"));
                bt.setActualDeparture(rs.getTime("actual_departure"));
                bt.setDelayMinutes(rs.getInt("delay_minutes"));
                bt.setRecordedBy(rs.getInt("recorded_by"));
                list.add(bt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}