package com.vivek;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class Flight {

	static Connection con = null;
	static PreparedStatement pst = null;
	static ResultSet rs = null;
	public static void createReservation(int pid, String pname, String pemail, long pcell, String pclass, String pseat,
			String pdep, String pdes, int id, int fid) throws SQLException {
		con = Connectivity.getConn();
		String s = "insert into booking values(?,?,?,?,?,?,?,?,?,?)";
		pst = con.prepareStatement(s);
		pst.setInt(1, pid);
		pst.setString(2, pname);
		pst.setString(3, pemail);
		pst.setLong(4, pcell);
		pst.setString(5, pclass);
		pst.setString(6, pseat);
		pst.setString(7, pdep);
		pst.setString(8, pdes);
		pst.setInt(9, id);
		pst.setInt(10, fid);
		int i = pst.executeUpdate();
		if(i>0)
			System.out.println("Record Inserted");
		else
			System.out.println("Not Inserted");
	}
	public static HashMap<String, Integer> check(String pdep, String pdes) throws SQLException {
		con = Connectivity.getConn();
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		String q = "select fno, ftime from flight where fdep=? and farr=?";
		pst = con.prepareStatement(q);
		pst.setString(1, pdep);
		pst.setString(2, pdes);
		rs = pst.executeQuery();
		while(rs.next()) {
			String time = rs.getTime("ftime").toString();
			int id = rs.getInt("fno");
			hm.put(time, id);
		}
		return hm;
	}
	public static void makeCancellation(int bid) throws SQLException {
		con = Connectivity.getConn();
		String q = "delete from booking where bid=?";
		pst = con.prepareStatement(q);
		pst.setInt(1, bid);
		int i = pst.executeUpdate();
		if(i>0)
			System.out.println("Cancellation Successful");
		else
			System.out.println("No flights found with given booking id");
	}

}
