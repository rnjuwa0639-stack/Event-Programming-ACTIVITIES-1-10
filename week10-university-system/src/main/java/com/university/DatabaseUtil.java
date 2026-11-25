package com.university;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** The "bridge" between the Java application and the MySQL database. */
public class DatabaseUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/university_db";
    private static final String USER = "root";
    // Reads the password from the DB_PASSWORD environment variable so it is never
    // committed to GitHub. Falls back to the placeholder below.
    private static final String PASSWORD = System.getenv().getOrDefault("DB_PASSWORD", "your_password");

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
