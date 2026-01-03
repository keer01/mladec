package com.orderapp;

import java.sql.Connection;
import java.sql.DriverManager;
 
public class DBUtil {
 
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/order_db",
                "root",
                "root@39"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
