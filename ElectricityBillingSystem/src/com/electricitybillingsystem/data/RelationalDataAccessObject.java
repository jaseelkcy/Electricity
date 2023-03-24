package com.electricitybillingsystem.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.electricitybillingsystem.connection.MySqlDatabaseInfo;

public abstract class RelationalDataAccessObject implements DataAccessObject
{
	
	MySqlDatabaseInfo my=new MySqlDatabaseInfo();
	public Connection connection;
	public PreparedStatement statement;
	@Override
	public void insert(Object object)
	{
		connection=my.getConnection();
		insertTo(object);	
	}

	@Override
	public void update(int id) {
		connection=my.getConnection();
		updateConsumer(id);
		
	}
	@Override
	public ResultSet login(int id,String password) {
		connection=my.getConnection();
		ResultSet resultset=login1(id,password);
		return resultset;
		
	}
	@Override
	public void calculate() {
		connection=my.getConnection();
		calc();
		
	}
	@Override
	public void view(int a)
	{
		connection=my.getConnection();
		viewBill(a);
	}
	@Override
	public void pay(int a)
	{
		connection=my.getConnection();
		payBill(a);
		
	}
	public void viewCustomers()
	{
		connection=my.getConnection();
		viewCust();	
	}
	protected abstract void insertTo(Object object);
	protected abstract ResultSet login1(int id,String password);
	protected abstract void updateConsumer(int id);
	protected abstract void calc();
	protected abstract void viewBill(int id);
	protected abstract void payBill(int id);
	protected abstract void viewCust();
}
