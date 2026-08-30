package com.bus.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/Bus_Record_Keeping_System?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "root";      
    private static final String PASSWORD = "YOUR_MYSQL_PASSWORD_HERE"; 

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // loads the MySQL JDBC driver
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
        }
        return connection;
    }
}