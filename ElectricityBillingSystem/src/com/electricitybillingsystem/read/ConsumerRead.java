package com.electricitybillingsystem.read;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import com.electricitybillingsystem.domain.Consumer;

public class ConsumerRead {
	 private InputStreamReader reader;
	 private BufferedReader br;
	 public Consumer readfromConsole()
	 {
		 try
		 {
			 Scanner sc=new Scanner(System.in);  
			 Consumer consumer=new Consumer();
			 reader = new InputStreamReader(System.in);
			 br = new BufferedReader(reader);
			 System.out.print("Enter Consumer name: ");
				String consumername = br.readLine();
			    consumer.setConsumerName(consumername);
			 System.out.print("Enter Address:");
			    String address=br.readLine();
			    consumer.setAddress(address);
			 System.out.print("Enter Email:");
			    String email=br.readLine();
			    consumer.setEmail(email);
			 System.out.println("Enter Password :");
			 String password=br.readLine();
			 consumer.setPassword(password);
		return consumer;
		 }
		 catch (IOException e)
		 {
				e.printStackTrace();
				System.out.print("Error");
				return null;
		 }		 
	 }

}


