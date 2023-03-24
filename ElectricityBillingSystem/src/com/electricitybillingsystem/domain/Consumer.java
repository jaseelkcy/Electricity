package com.electricitybillingsystem.domain;

import java.time.LocalDate;
import java.sql.Date;

public class Consumer {
	int consumerno;
	String consumername,address,email,password;
	public Consumer()
	{
		
	}
	public Consumer(int consumerno,String consumername,String address,String email,String password)
	{
		this.consumerno=consumerno;
		this.consumername=consumername;
		this.address=address;
		this.email=email;
		this.password=password;
	}
	public void setConsumerNo(int consumerno)
	{
		this.consumerno=consumerno;
	}
	public void setConsumerName(String consumername)
	{
		this.consumername=consumername;
	}
	public void setAddress(String address)
	{
		this.address=address;
	}
	public void setEmail(String email)
	{
		this.email=email;
	}
	public void setPassword(String password)
	{
		this.password=password;
	}
	
	public int getConsumerno()
	{
		return this.consumerno;
	}
	public String getConsumerName()
	{
		return this.consumername;
	}
	public String getAddress()
	{
		return this.address;
	}
	public String getEmail()
	{
		return this.email;
	}
	public String getPassword()
	{
		return this.password;
	}
	
	public String toString()
	{
		return(this.consumerno+","+this.consumername+","+this.address+","+this.email+","+this.password);
	}
}
