package com.example.Linkedin.Scrapper.utils;

import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class ConnectionUtility {

    private final static String URL = "jdbc:mysql://localhost:3306/linkedin_scrapperDb";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "root";
    private static Connection connection;

    public static Connection getConnection() {

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException ex) {
            System.out.println("Failed to create the database connection.");
        }
        return connection;
    }


}
