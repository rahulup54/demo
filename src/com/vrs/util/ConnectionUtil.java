package com.vrs.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionUtil {

	private static Connection con = null;
	private static ConnectionUtil util = null;
	
	private ConnectionUtil() {}
	
	public static ConnectionUtil getInstance() {
		if(null == util) {
			util = new ConnectionUtil();
		}
		return util;
	}
	
	public static Connection getConnection() 
	{
		try 
		{
			Class.forName("com.postgres.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test1", "postgres", "RAHUL@123");
		} 
		catch (Exception e)
		{
			System.err.println("Error getting DB connection." + e);
		}
		return con;
	}
	
	public static void closeConnection(Connection con) 
	{
		try 
		{
			if(null != con) {
				//con.commit();
				con.close();
			}
		}
		catch (Exception e) 
		{
			System.err.println("Error closing DB connection." + e);
		}
	}
}
