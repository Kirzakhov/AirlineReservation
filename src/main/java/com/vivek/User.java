package com.vivek;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class User {
	static Connection con = null;
	static PreparedStatement pst = null;
	static ResultSet rs = null;
	public static void register(int id, String name, String pass, long cell, String email, String add) throws SQLException {
		con = Connectivity.getConn();
		String q = "insert into user values(?,?,?,?,?,?)";
		pst = con.prepareStatement(q);
		pst.setInt(1, id);
		pst.setString(2, name);
		pst.setString(3, pass);
		pst.setLong(4, cell);
		pst.setString(5, email);
		pst.setString(6, add);
		int i = pst.executeUpdate();
		if(i>0)
			System.out.println("Record inserted");
		else
			System.out.println("Not Inserted");
	}
	public static int login(String email, String pass) throws SQLException {
		con = Connectivity.getConn();
		String q = "select uid from user where uemail=? and upass=?";
		pst = con.prepareStatement(q);
		pst.setString(1, email);
		pst.setString(2, pass);
		rs = pst.executeQuery();
		if(rs.next())
			return rs.getInt(1);
		else
			return 0;
	}

}
