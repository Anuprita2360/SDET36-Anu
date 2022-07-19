package org.vtiger.practice;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WriteTheDataInExcel {

	public static void main(String[] args) throws IOException
	{
		FileInputStream fisExcel=new FileInputStream("./src/test/resources/Contacts.xlsx");
		
		Workbook workbook = WorkbookFactory.create(fisExcel);
		workbook.getSheet("Sheet1").getRow(2).createCell(2).setCellValue("pass");
		
		
		FileOutputStream fosExcel=new FileOutputStream("./src/test/resources/Contacts.xlsx");
		workbook.write(fosExcel);
		
		System.out.println("data entered");
		workbook.close();

	}

}
