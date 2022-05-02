package com.vivek;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connectivity {
	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/vivek";
	private static String un = "root";
	private static String up = "0000";
	
	public static Connection getConn() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,un,up);
			if(con!=null)
				System.out.println("Connected...");
			else
				System.out.println("Not Connected!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}
