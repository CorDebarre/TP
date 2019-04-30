package com.config;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BddConnection {
	private static Connection connect = null;
	private static String ip = "127.0.0.1";
	private static String bdd = "maven";
	private static String user = "root";
	private static String password = "";
	

	private BddConnection() {
		connect = null;
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connect = DriverManager.getConnection(
					"jdbc:mysql://"+ip+"/"+bdd+"?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC",user,password);
			
			
		} catch (SQLException e) {
			 e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         System.exit(0);
		}
	
	
	}

	public static Connection getInstance() {
		if (connect == null) {
			new BddConnection();
		}
		return connect;
	}
}
