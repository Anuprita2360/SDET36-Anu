package org.vtiger.practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FetchTheDataFromPropertyFile {

	public static void main(String[] args) throws IOException 
	{
		FileInputStream fis=new FileInputStream("./src/test/resources/commonData.properties");
		Properties properties=new Properties();
		properties.load(fis);
		
		String URL=properties.getProperty("url");
		String USERNAME = properties.getProperty("username");
		String PASSWORD = properties.getProperty("password");
		System.out.println(URL);
		System.out.println(USERNAME);
		System.out.println(PASSWORD);

	}

}
