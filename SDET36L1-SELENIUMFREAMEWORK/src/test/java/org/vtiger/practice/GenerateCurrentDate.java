package org.vtiger.practice;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GenerateCurrentDate {

	public static void main(String[] args) 
	{
		Date date=new Date();
		System.out.println(date);
		
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		String data = sdf.format(date);
		System.out.println(data);

	}

}
