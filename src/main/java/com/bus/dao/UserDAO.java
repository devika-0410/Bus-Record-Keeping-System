package com.bus.dao;

import com.bus.model.User;
import com.bus.util.DBConnection;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.*;

public class UserDAO {

    public boolean registerUser(User user) {
        String sql = "INSERT INTO users (full_name, email, password, phone, role) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // Hash the password before storing - never store plain text
            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

            ps.setString(1, user.getFullName());
            ps.setString(2, user.getEmail());
            ps.setString(3, hashedPassword);
            ps.setString(4, user.getPhone());
            ps.setString(5, user.getRole());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean emailExists(String email) {
        String sql = "SELECT user_id FROM users WHERE email = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Validate login by comparing the hashed password
    public User validateLogin(String email, String password) {
        String sql = "SELECT * FROM users WHERE email = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String storedHash = rs.getString("password");

                // Compare the entered plain password against the stored hash
                if (BCrypt.checkpw(password, storedHash)) {
                    User user = new User();
                    user.setUserId(rs.getInt("user_id"));
                    user.setFullName(rs.getString("full_name"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(storedHash);
                    user.setPhone(rs.getString("phone"));
                    user.setRole(rs.getString("role"));
                    return user;
                }
            }
            return null; // either email not found, or password didn't match

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}