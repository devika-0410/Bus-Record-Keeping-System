package com.bus.dao;

import com.bus.model.Route;
import com.bus.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RouteDAO {

    public List<Route> getAllRoutes() {
        List<Route> routes = new ArrayList<>();
        String sql = "SELECT * FROM routes";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Route r = new Route();
                r.setRouteId(rs.getInt("route_id"));
                r.setSourcePlace(rs.getString("source_place"));
                r.setDestinationPlace(rs.getString("destination_place"));
                r.setDistanceKm(rs.getDouble("distance_km"));
                routes.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return routes;
    }
    
    public Route getRouteById(int routeId) {
        String sql = "SELECT * FROM routes WHERE route_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, routeId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Route r = new Route();
                r.setRouteId(rs.getInt("route_id"));
                r.setSourcePlace(rs.getString("source_place"));
                r.setDestinationPlace(rs.getString("destination_place"));
                r.setDistanceKm(rs.getDouble("distance_km"));
                return r;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}