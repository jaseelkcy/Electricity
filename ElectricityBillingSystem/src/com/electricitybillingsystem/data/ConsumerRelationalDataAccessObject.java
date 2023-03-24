package com.electricitybillingsystem.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import com.electricitybillingsystem.domain.Consumer;

public class ConsumerRelationalDataAccessObject extends RelationalDataAccessObject{
	private ResultSet resultSet;
	Scanner sc=new Scanner(System.in);
	public void insertTo(Object object)
	{
		try {
			Consumer consumer=(Consumer)object;
			statement = connection.prepareStatement(
					"INSERT INTO consumers(consumername, address, email,password) values(?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, consumer.getConsumerName());
			statement.setString(2, consumer.getAddress());
			statement.setString(3, consumer.getEmail());
			statement.setString(4, consumer.getPassword());
			System.out.println("Inserting consumer data into customers table");
			statement.executeUpdate();
			System.out.println("consumer saved successfully.");
		    }
		catch (SQLException e)
		{
			e.printStackTrace();
		} 
	}
	@Override
	protected ResultSet login1(int id, String password)
	{
		try {
		statement = connection.prepareStatement("SELECT * FROM consumers WHERE consumerno=? AND password=?");
		statement.setInt(1, id);
		statement.setString(2, password);
		resultSet=statement.executeQuery();
		
		if(resultSet.next())
		{
			System.out.println("Login Succesfull");
			return resultSet;
		}
		else
		{
			System.out.println("Invalid");
		}
		}
		catch(SQLException e)
		{
			e.printStackTrace();			
		}
		return null;
	}
	@Override
	protected void updateConsumer(int id) {
		System.out.println("Enter Consumer Name:");
		String cname =sc.nextLine();
		System.out.print("Enter Address:");
		String caddress=sc.nextLine();
		System.out.print("Enter Email:");
	    String cemail=sc.next();
	    try { 
	
			 System.out.println("Enter Password :");
			 String cpassword=sc.next();
			 statement = connection.prepareStatement(
						"UPDATE consumers SET consumername=?,address=?,email=?,password=?"
						+ "WHERE consumerno=?");
				statement.setString(1,cname);
				statement.setString(2,caddress);
				statement.setString(3,cemail);
				statement.setString(4,cpassword);
				statement.setInt(5, id);
				statement.executeUpdate();
				System.out.println("Profile updated");
	    	}

	    catch(SQLException e)
		{
			e.printStackTrace();			
		}
		
	}

	@Override
	protected void viewBill(int id) {
		try {
			statement = connection.prepareStatement("select * from consumers inner join bill ON consumers.consumerno=bill.consumerno WHERE consumers.consumerno=?");
			statement.setInt(1, id);
			resultSet=statement.executeQuery();
			
			if(resultSet.next())
			{
				System.out.println();
				System.out.println("ConsumerNo"+"\t"+"ConsumerName"+"\t"+
						"address"+"\t\t"+"email"+"\t\t"+
						"BillAmount"+"\t\t"+"Fine"+"\t\t"+"DueDate"
						+"\t\t"+"Status");
				String state;
				if(resultSet.getBoolean("status")==true)
				{
					state="paid";
				}
				else
				{
					state="Unpaid";
				}
				System.out.println(resultSet.getInt("consumerno") +"\t\t"+
						resultSet.getString("consumername")+"\t\t"+
						resultSet.getString("address") + "\t" +
						resultSet.getString("email") + "\t" +
						resultSet.getInt("amount")+ "\t\t\t"+
						resultSet.getInt("fine")+ "\t\t" +
						resultSet.getDate("duedate")+ "\t" +
						state);
				
			}
			else
			{
				System.out.println("No Data Found");
			}
		}
		catch(SQLException e)
		{
			System.out.println("Error");
		}
	}
	@Override
	protected void payBill(int id) {
		try
		{
		statement = connection.prepareStatement("DELETE FROM bill where consumerno=?");
		statement.setInt(1, id);
		int a=statement.executeUpdate();
		if(a==0)
		{
			System.out.println("No Bill found");
		}
		else
		{
		System.out.println();
		System.out.println("Bill Paid");
		}
		}
		catch(SQLException e)
		{
			System.out.println("Error");
		}
	}
	@Override
	protected void viewCust() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void delete(int b) {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void calc() {
		// TODO Auto-generated method stub
		
	}
	
}


