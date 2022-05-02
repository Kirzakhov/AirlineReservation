package com.vivek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class AirlineMain {

	public static void main(String[] args) throws IOException, SQLException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Do you want to Register(R) or Login(L)?");
		char input = br.readLine().trim().toUpperCase().charAt(0);
		if(input=='R') {
			System.out.println("Enter id");
			int id = Integer.parseInt(br.readLine());
			System.out.println("Enter name");
			String name = br.readLine();
			System.out.println("Enter password");
			String pass = br.readLine();
			System.out.println("Enter cell number");
			long cell = Long.parseLong(br.readLine());
			System.out.println("Enter email");
			String email = br.readLine();
			System.out.println("Enter address");
			String add = br.readLine();
			User.register(id, name, pass, cell, email, add);
		}
		else if(input=='L') {
			System.out.println("Enter email");
			String email = br.readLine();
			System.out.println("Enter password");
			String pass = br.readLine();
			int id = User.login(email, pass);
			if(id>0) {
				System.out.println("Press R for reservation, C for cancellation, L for Logout");
				char ch = br.readLine().trim().toUpperCase().charAt(0);
				boolean flag = true;
				while(flag) {
					if(ch=='R') {
						System.out.println("Enter id");
						int pid = Integer.parseInt(br.readLine());
						System.out.println("Enter passenger name");
						String pname = br.readLine();
						System.out.println("Enter passenger email");
						String pemail = br.readLine();
						System.out.println("Enter passenger contact number");
						long pcont = Long.parseLong(br.readLine());
						System.out.println("Enter booking class");
						String pclass = br.readLine();
						System.out.println("Select seat number");
						String pseat = br.readLine();
						System.out.println("Enter departure location");
						String pdep = br.readLine();
						System.out.println("Enter destination");
						String pdes = br.readLine();
						HashMap<String, Integer> check = Flight.check(pdep, pdes);
						if(check.size()>0) {
							for(Map.Entry<String, Integer> m : check.entrySet()) {
								System.out.println(m.getKey());
							}
							System.out.println("Please select the time suitable for your flight");
							String time = br.readLine();
							if(check.containsKey(time)) {
								int fid = check.get(time);
								Flight.createReservation(pid, pname, pemail, pcont, pclass, pseat, pdep, pdes, id, fid);
							}
							else {
								System.out.println("No flights found for given time");
							}
						}
						else
							System.out.println("No Fights found");	
					}
					else if(ch=='C') {
						System.out.println("Enter booking id of the flight you want to cancel");
						int bid = Integer.parseInt(br.readLine());
						Flight.makeCancellation(bid);
					}
					else if(ch=='L') {
						break;
					}
					else {
						System.out.println("Please provide a proper input");
					}
					System.out.println("Do you wanna continue? Y/N");
					char yn = br.readLine().trim().toUpperCase().charAt(0);
					if(yn=='N')
						break;
				}
			}
			else {
				System.out.println("Incorrect email/password");
			}
		}
		else {
			System.out.println("Please provide a valid input");
		}
	}

}
