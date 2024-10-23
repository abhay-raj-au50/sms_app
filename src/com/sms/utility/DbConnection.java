package com.sms.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	// db connection code
	static Connection con;
	
//	DbConnection dbConnect;
//	{
//		dbConnect = new DbConnection();
//	}
//	public  DbConnection getInstance() {
//		return dbConnect;
//	}
	public static void dbConnect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("driver loaded");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	try {
		con =	DriverManager.getConnection("jdbc:mysql://localhost:3306/fsd_oct_sms_db","root","123456");
	System.out.println("Connection est...");
	} catch (SQLException e) {
	
		e.printStackTrace();
	}
	}
	public static void dbClose() {
		try {
			con.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
//	public static void main(String[] args) {
////		DbConnection.dbConnection = new DbConnection();
//		DbConnection.dbConnect();
//		DbConnection.dbClose();
//	}
}
//ctrl+shift+o   to import 
//press ctrl+ space to import and fullfil