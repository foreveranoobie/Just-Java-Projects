package com.hibernate.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestJdbc {
    public static void main(String[] args) throws SQLException {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/springdemo";
        String username = "admin";
        String password = "admin";
        Connection connection =
                DriverManager.getConnection(jdbcUrl, username, password);
        connection.close();
    }
}
