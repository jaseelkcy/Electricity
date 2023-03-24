package com.electricitybillingsystem.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;

import com.electricitybillingsystem.connection.MySqlDatabaseInfo;
import com.electricitybillingsystem.data.DataAccessObject;
import com.electricitybillingsystem.domain.Consumer;

public class AdminService {
	Scanner sc=new Scanner(System.in);
	MySqlDatabaseInfo my=new MySqlDatabaseInfo();
	public Connection connection;
	public PreparedStatement statement;
	ResultSet resultSet;
	private DataAccessObject dao;
	public AdminService (DataAccessObject dao)
	{
		super();
		this.dao=dao;
	}
	public boolean login()
	{
		try
		{
			do
			{
		System.out.print("Enter Admin id:");
		int id=sc.nextInt();
		System.out.print("Enter Admin password:");
		String pass=sc.next();
		resultSet=dao.login(id, pass);
			}
			while(resultSet==null);
       if (resultSet.getInt("adminid")==111 && resultSet.getString("adminpass").compareTo("admin")==0)
       {
    	   System.out.println("Login Successfull");
    	   return true;
       }
		
		}
		catch(SQLException e)
		{
			e.printStackTrace();			
		}
		return false;
	}
	public void addConsumer(Consumer consumer)
	{
		dao.insert(consumer);
	}
	public void calculateBill()
	{
    	dao.calculate();
	}
	public void viewCustomer()
	{
		dao.viewCustomers();
	}
	public void viewBill()
	{
		 System.out.println("Enter ConsumerNo");
		 int cno=sc.nextInt();
		dao.view(cno);
	}
	public void viewAllBill()
	{
		connection=my.getConnection();
		try {
			statement = connection.prepareStatement("select * from consumers inner join bill ON consumers.consumerno=bill.consumerno");
			resultSet=statement.executeQuery();
			ResultSetMetaData rsmd = resultSet.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			String state="Unpaid";
			System.out.println();
			System.out.println("ConsumerNo"+"\t"+"ConsumerName"+"\t"+
					"address"+"\t\t"+"email"+"\t\t"+
					"BillAmount"+"\t\t"+"Fine"+"\t\t"+"DueDate"
					+"\t\t"+"Status");
			while(resultSet.next())
			{
				 for(int i=1;i<=columnsNumber;i++)
				  {
				System.out.println(resultSet.getInt("consumerno") + "\t\t" +
						resultSet.getString("consumername") +"\t\t"+
						resultSet.getString("address") + "\t\t" +
						resultSet.getString("email") + "\t\t"+
						resultSet.getInt("amount")+ "\t\t\t"+
						resultSet.getInt("fine")+ "\t\t" +
						resultSet.getDate("duedate")+ "\t" +
						state);
						break;
				  }
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();			
		}
	}

}
