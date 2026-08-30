package com.bus.dao;

import com.bus.model.Bus;
import com.bus.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BusDAO {

    public List<Bus> getAllBuses() {
        List<Bus> buses = new ArrayList<>();
        String sql = "SELECT b.*, r.source_place, r.destination_place " +
                     "FROM buses b JOIN routes r ON b.route_id = r.route_id";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Bus b = new Bus();
                b.setBusId(rs.getInt("bus_id"));
                b.setBusNumber(rs.getString("bus_number"));
                b.setTotalSeats(rs.getInt("total_seats"));
                b.setRouteId(rs.getInt("route_id"));
                b.setSourcePlace(rs.getString("source_place"));
                b.setDestinationPlace(rs.getString("destination_place"));
                buses.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return buses;
    }
}