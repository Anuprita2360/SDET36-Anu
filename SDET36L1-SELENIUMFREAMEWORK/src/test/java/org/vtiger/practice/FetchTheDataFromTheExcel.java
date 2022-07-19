package org.vtiger.practice;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FetchTheDataFromTheExcel {

	public static void main(String[] args) throws IOException 
	{
		DataFormatter dataFormat=new DataFormatter();
		FileInputStream fisExcel=new FileInputStream("./src/test/resources/Contacts.xlsx");
		Workbook workbook = WorkbookFactory.create(fisExcel);
		//String data = dataFormat.formatCellValue(workbook.getSheet("Contacts").getRow(6).getCell(1));
		//System.out.println(data);

		Sheet sheet = workbook.getSheet("Contacts");


		//if we don't know the how many ROWS have data then we use getLastRowNum() method 
	/*	for(int i=0;i<=sheet.getLastRowNum();i++)
		{
			String data = dataFormat.formatCellValue(sheet.getRow(i).getCell(1));

			if(data.equalsIgnoreCase("organisationName"))
			{
				System.out.println(sheet.getRow(i+1).getCell(1));
				break;
			}

		}*/
		
		int rowNum=sheet.getLastRowNum();
		int cellNum=sheet.getRow(0).getLastCellNum();
		String[][] str=new String[rowNum][cellNum];
		int i,j;
		
		for( i=0;i<=rowNum;i++)
		{
			for( j=0;j<cellNum;j++)
			{
				str[i-1][j]=dataFormat.formatCellValue(sheet.getRow(i).getCell(j));
			}
			
			
			    System.out.println(str[i-1][1]);
			}
		
		workbook.close();

	}
}
