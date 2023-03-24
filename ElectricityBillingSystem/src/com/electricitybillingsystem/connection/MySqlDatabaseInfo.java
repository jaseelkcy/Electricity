package com.electricitybillingsystem.connection;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class MySqlDatabaseInfo implements DataBaseInfo{
	static private String userName="root";
	static private String password="root";
	static private String host="localhost";
	static private String port="3306";
	static private String schema="electricity";
	public static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	private Connection connection;
	private PreparedStatement statement;

	public String getUrl()
	{
		return new StringBuilder().append("jdbc:mysql://").append(host)
		.append(":").append(port).append("/").append(schema)
		.append("?user=").append(userName).append("&password=")
		.append(password).toString();
	}
	public String getDriverName()
	{
		return DRIVER_NAME;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public String getUserName()
	{
		return userName;
	}
	
	public Connection getConnection()
	{
		try {
			Class.forName(DRIVER_NAME);
			//System.out.println("Connector loaded successfully");
			connection = DriverManager.getConnection(getUrl());
			//System.out.println("Connected with host:port/database.");
			return connection;
		    }
	    catch (SQLException ex)
		    {
			ex.printStackTrace();
		    } 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		    }
		return null;
	}
	
	public void closeConnection()
	{
		if (statement != null)
		{
			try {
				statement.close();
			    } 
			catch (SQLException e)
			   {
				e.printStackTrace();
			   }
		}
		
		if (connection != null)
		{
			try {
				connection.close();
			    } 
			catch (SQLException e)
			    {
				e.printStackTrace();
			    }
		}
	}	
}
