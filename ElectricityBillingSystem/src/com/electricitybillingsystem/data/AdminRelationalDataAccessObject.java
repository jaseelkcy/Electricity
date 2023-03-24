package com.electricitybillingsystem.data;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.electricitybillingsystem.domain.Consumer;

public class AdminRelationalDataAccessObject extends RelationalDataAccessObject{
	private ResultSet resultSet;
	Scanner sc=new Scanner(System.in);

	@Override
	protected void insertTo(Object object) {
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
	protected ResultSet login1(int id, String password) {
		try {
			statement = connection.prepareStatement("SELECT * FROM admin WHERE adminid=? AND adminpass=?");
			statement.setInt(1, id);
			statement.setString(2, password);
			resultSet=statement.executeQuery();
			
			if(resultSet.next())
			{
				return resultSet;
			}
			else
			{
				System.out.println("Invalid Id or Password");
			}
			}
			catch(SQLException e)
			{
				e.printStackTrace();			
			}
			return null;
	}

	@Override
	protected void calc()
	{
		try
    	{
    		try
         	{
		double amount;
		long fine,daysDiff;
   	   SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		System.out.print("Enter consumer no:");
		int cno=sc.nextInt();
		System.out.print("Enter units:");
		int unit=sc.nextInt();
		if(unit < 100)  
        {  
            amount = unit*1.20;  
        }
		else if(unit < 300)  
        {   
            amount= 100* 1.20+(unit - 100)*2;
        }
		
		else 
        {   
            amount= 100 * 1.20 + 200 * 2 + (unit - 300) * 3;
        }
		System.out.print("Enter due date:");
		String duedate = sc.next();

	    System.out.println("Enter current date:");
	    String curdate=sc.next();
		java.util.Date dueDate = formatter.parse(duedate);
		if (!duedate.equals(formatter.format(dueDate)))
				{
			System.out.print("Enter correct formatted date date:");
				}
			java.util.Date curDate = formatter.parse(curdate);
			java.sql.Date Duedate = new java.sql.Date( dueDate.getTime());
			java.sql.Date Curdate = new java.sql.Date( curDate.getTime());

		        long da=Duedate.getTime();
		     	long da1=Curdate.getTime();
		     	long timeDiff = da1 - da;
		     	if(timeDiff<0 || timeDiff==0)
		     	{
		     		daysDiff=0;
		     	}
		     	else
		     	 daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
		fine=daysDiff*5;
     	statement = connection.prepareStatement(
				"INSERT INTO bill(consumerno,amount,fine,duedate)values(?,?,?,?)");
     	statement.setInt(1, cno);
     	statement.setDouble(2, amount);
     	statement.setLong(3, fine);
     	statement.setDate(4, Duedate);
     	System.out.println("Inserting data into bill table");
		statement.executeUpdate();
		System.out.println("bill saved successfully.");
		System.out.println();
		statement=connection.prepareStatement(
				"SELECT * FROM bill WHERE consumerno=?");
		statement.setInt(1,cno);
		resultSet=statement.executeQuery();
		System.out.println("consumerno" + "\t" +
		"amount" +"\t\t"+"fine"+"\t"+"duedate");
		while(resultSet.next())
		{
		System.out.println(resultSet.getInt("consumerno") + "\t\t" +
				resultSet.getInt("amount") +"\t\t"+
				resultSet.getInt("fine") + "\t\t" +
				resultSet.getDate("duedate")); 
		}
     	}
     	catch(SQLException e)
    	{
    		System.out.print("Error Occured:Consumerno Not Found");
    	}
    	}
    	catch(ParseException e)
    	{
    		System.out.print("error");
    	}
		}

	@Override
	protected void viewCust() {
		try {
		statement = connection.prepareStatement("SELECT * FROM consumers");
		resultSet=statement.executeQuery();
		ResultSetMetaData rsmd = resultSet.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		System.out.println("ConsumerNo"+"\t"+"ConsumerName"+"\t"+
				"address"+"\t\t"+"email"+"\t\t"+"password");
		while(resultSet.next())
		{
			 for(int i=1;i<=columnsNumber;i++)
			  {
			System.out.println(resultSet.getInt("consumerno") + "\t\t" +
					resultSet.getString("consumername") +"\t\t"+
					resultSet.getString("address") + "\t\t" +
					resultSet.getString("email") + "\t" +
					resultSet.getString("password"));
			    break;
			  }
		}
		}
		catch(SQLException e)
		{
			e.printStackTrace();			
		}
		
	}
	@Override
	protected void updateConsumer(int id) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int b) {
		// TODO Auto-generated method stub
		
	}
 }


