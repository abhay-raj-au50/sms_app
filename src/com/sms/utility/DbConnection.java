package com.sms.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static Connection con;

    // Method to establish a database connection
    public static Connection dbConnect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
//            System.out.println("Driver loaded");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fsd_oct_sms_db", "root", "123456");
//            System.out.println("Connection established");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    // Method to close the database connection
    public static void dbClose() {
        if (con != null) {
            try {
                con.close();
                System.out.println("Connection closed");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
