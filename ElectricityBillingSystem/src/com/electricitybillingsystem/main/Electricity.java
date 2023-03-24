package com.electricitybillingsystem.main;

import java.sql.ResultSet;
import java.util.Scanner;


import com.electricitybillingsystem.data.AdminRelationalDataAccessObject;
import com.electricitybillingsystem.data.ConsumerRelationalDataAccessObject;
import com.electricitybillingsystem.data.DataAccessObject;
import com.electricitybillingsystem.domain.Consumer;
import com.electricitybillingsystem.read.ConsumerRead;
import com.electricitybillingsystem.service.AdminService;
import com.electricitybillingsystem.service.ConsumerService;
 
public class Electricity
{
	public static void main(String[] args)
	{
		int choice,ch,flag=0;
		boolean check,b=true;
		char ask,ask1;
		Scanner sc=new Scanner(System.in);
		System.out.println("###############     ELECTRICITY BILLING SYSTEM     ###############");
		System.out.println();
		do
		{
		System.out.println("1.Register new Consumer"+"\n"+"2.Admin Login"+"\n"+"3.Consumer Login"+"\n"+"4.Exit");
		System.out.println("Enter Your Choice:");
		choice=sc.nextInt();
		   ConsumerRead consumerread=new ConsumerRead();
		   
		   DataAccessObject dao=new ConsumerRelationalDataAccessObject();
		   ConsumerService consumerservice=new ConsumerService(dao);
		   
		   DataAccessObject dao1=new AdminRelationalDataAccessObject();
		   AdminService adminservice=new AdminService(dao1);
		   
		if(choice==1)
		{
	      Consumer consumer=consumerread.readfromConsole();
	      consumerservice.addConsumer(consumer);
		}
		
		if(choice==2)
		{
			System.out.println();
			System.out.println("                  ADMIN LOGIN");
			check=adminservice.login();
			  if(check==true)
			  {
				  do
				  {
				  System.out.println();
					System.out.println("                  ADMIN");
					System.out.println();
				  System.out.println("1.Register New Consumer"+"\n"+"2.View Consumers"+"\n"
					+"3.Calculate Bill"+"\n"+"4.View All Bill"+"\n"+"5.View Bill by Id"
						  +"\n"+"6.Exit");
				  System.out.println("Enter Your Choice:");
				  ch=sc.nextInt();
				  switch(ch)
					{
					case 1: ConsumerRead consumerread1=new ConsumerRead();
							Consumer consumer=consumerread1.readfromConsole();
							adminservice.addConsumer(consumer);
							break;
					case 2:adminservice.viewCustomer();
							break;
					case 3: adminservice.calculateBill();
							break;
					case 4:	adminservice.viewAllBill();
							break;
					case 5:	adminservice.viewBill();
							break;
					case 6:b=false;
						break;
					}
				  System.out.println();
				  }
				  
				  while(b);
			  }
		}
		
		if(choice==3)
		{
			ResultSet resultset;
			 System.out.println("             CONSUMER LOGIN");
				System.out.println();
				do
				{
				 resultset= consumerservice.login1();
	
				}
				while(resultset==null);
				
				do
				{
					flag=0;
				System.out.println();
				System.out.println("                  CONSUMER");
				System.out.println();
				System.out.println("1.Display Profile"+"\n"+"2.Update Profile"+"\n"+"3.View Bill"+"\n"+"4.Pay Bill"+"\n"+"5. Break");
				System.out.println();
				System.out.println("Enter Your Choice: ");
				ch=sc.nextInt();
				switch(ch)
				{
				case 1:consumerservice.display(resultset);
						break;
				case 2:consumerservice.update(resultset);
						flag=1;
				        break;
				case 3:consumerservice.ViewBill(resultset);
						break;
				case 4:consumerservice.payBill(resultset);
						break;
				case 5:break;
				}
				System.out.println();
				System.out.println("Continue?Y/N:");
				ask=sc.next().charAt(0);
				
				}
				while(ask=='y' && flag==0);

		} 
		if(choice==4)
		{
			System.out.println("Exited");
			break;
		}
		System.out.println();
		System.out.println("Do you want to Exit? Y/N:");
		  ask1=sc.next().charAt(0);
		  if(ask1=='y')
			  System.out.println("Exited");
		}
		while(ask1=='n');
	 }	
  }

