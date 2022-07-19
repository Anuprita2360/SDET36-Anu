package org.vtiger.practice;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DatabasePractice {
	static Driver driver;
	static Connection connection;
	static Statement statement;
	static 	int result;
public static void main(String[] args)
	{
		try {
			driver = new Driver();
			DriverManager.registerDriver(driver);
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tyss","root","root");
			statement = connection.createStatement();
			//int result = statement.executeUpdate("insert into sdet36(empid,empname,phoneNumber,emailid,address) values(1004,'Anuprit',9874563485,'anuprit@gmail.com','Hyderabad'),(1002,'Nilesh',9874563585,'nilesh@gmail.com','Bhopal');");
			result = statement.executeUpdate("update sdet36 set address='Banglore' where empid=1000;");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		if(result==1)           
		{
			System.out.println("data updated into database");
		}
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
