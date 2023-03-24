package com.electricitybillingsystem.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.electricitybillingsystem.data.DataAccessObject;
import com.electricitybillingsystem.domain.Consumer;

public class ConsumerService {
	Scanner sc=new Scanner(System.in);
	private DataAccessObject dao;
	public ConsumerService(DataAccessObject dao)
	{
		super();
		this.dao=dao;
	}
	
	public ResultSet login1()
	{
		System.out.print("Enter Consumer Id:");
		int consid=sc.nextInt();
		System.out.print("Enter Password:");
		String pass=sc.next();
		ResultSet resultset=dao.login(consid,pass);
		return(resultset);
	}
	public void addConsumer(Consumer consumer)
	{
		dao.insert(consumer);
	}
	
	public void update(ResultSet resultset)
	{
		
		try
		{
		int id=resultset.getInt("consumerno");
		dao.update(id);
		}
		catch(SQLException e)
		{
			e.printStackTrace();			
		}
	}
	public void display(ResultSet resultSet)
	{
		System.out.println("ConsumerNo"+"\t"+"ConsumerName"+"\t"+
		"address"+"\t\t"+"email"+"\t\t"+"password");
		try
		{
		System.out.println(resultSet.getInt("consumerno") + "\t\t" +
				resultSet.getString("consumername") +"\t\t"+
				resultSet.getString("address") + "\t\t" +
				resultSet.getString("email") + "\t" +
				resultSet.getString("password"));	
		}
		catch(SQLException e)
		{
			System.out.println("Error");
		}
	}
	
	public void ViewBill(ResultSet resultSet)
	{
		try
		{
		int cno=resultSet.getInt("consumerno");
		dao.view(cno);	
		}
		catch(SQLException e)
		{
			System.out.println("Error");
		}
	}
	public void payBill(ResultSet resultSet)
	{
		try
		{
		int cno=resultSet.getInt("consumerno");
		dao.pay(cno);
		}
		catch(SQLException e)
		{
			System.out.println("Error");
		}
	}
}
