package com.electricitybillingsystem.data;
import java.sql.ResultSet;
import java.util.ArrayList;

public interface DataAccessObject {
	void insert(Object object);
	void calculate();
	void update(int id);
	ResultSet login(int id,String password);
	void delete(int b);
	void view(int a);
	void pay(int a);
	void viewCustomers();
}
