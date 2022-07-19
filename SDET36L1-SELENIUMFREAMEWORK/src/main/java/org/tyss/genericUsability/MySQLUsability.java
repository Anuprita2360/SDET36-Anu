package org.tyss.genericUsability;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.mysql.cj.jdbc.Driver;
import com.mysql.cj.xdevapi.Statement;

public class MySQLUsability 
{
	private Connection connection;
	private Driver dbDriver;

	
	
	/**
	 * This method is used to establish the database connection
	 * @param url
	 * @param userName
	 * @param password
	 */
	
	public void getConnectionWithDB(String url,String userName,String password)
	{
		
		try {
			dbDriver=new Driver();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			DriverManager.registerDriver(dbDriver);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			connection=DriverManager.getConnection(url, userName, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method is used to modify the data in database
	 * @param query
	 * @param columnNameOrColumnIndex
	 * @return
	 */
	
	public List<String> getDataFromDB(String query,String columnNameOrColumnIndex)
	{
		ResultSet results = null;
		List<String> list=new ArrayList<String>();
		
		String s1="";
		
		for(int i=0;i<columnNameOrColumnIndex.length();i++)
		{
			if(Character.isDigit(columnNameOrColumnIndex.charAt(i)))
			{
				s1=s1+columnNameOrColumnIndex.charAt(i);
			}
			else
			{
				break;
			}
		}
		try {
			results=connection.createStatement().executeQuery(query);
			System.out.println("Data entered to database");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			while(results.next())
			{
				if(columnNameOrColumnIndex.length()==s1.length())
				{
					list.add(results.getString(Integer.parseInt(s1)));
				}
				else
				{
					list.add(results.getString(columnNameOrColumnIndex));
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * This method is used to verify whether the expected data is present or not
	 * @param query
	 * @param columnNameOrColumnIndex
	 * @param expectedData
	 * @return
	 */
		
		public boolean verifyDataInDB(String query,String columnNameOrColumnIndex,String expectedData)
		{
			List<String> list =getDataFromDB(query,columnNameOrColumnIndex);
			
			System.out.println(list);
			boolean flag=false;
			for(String data:list)
			{
				if(data.equalsIgnoreCase(expectedData))
				{
					flag=true;
					break;
				}
			}
			return flag;
		}
		
		/**
		 * This method is used to update the data to database
		 * @param UpdateQuery
		 * @return
		 */
		
		public int updateDataToDatabase(String UpdateQuery)
		{
			int result=0;
			try {
				int results = connection.createStatement().executeUpdate(UpdateQuery);
				System.out.println("Data entered to database");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return result;
			
		}
		
		/**
		 * This method is used to close the connection
		 */
		
		public void closeDataBaseConnection()
		{
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}


