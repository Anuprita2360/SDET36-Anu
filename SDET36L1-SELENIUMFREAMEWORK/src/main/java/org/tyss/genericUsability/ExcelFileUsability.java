package org.tyss.genericUsability;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * This class contains reusable methods for Excel file
 * @author Anuprita raut
 *
 */

public class ExcelFileUsability 
{
	private Workbook workbook;
	private Sheet sheet;
	private String excelPath;

	/**
	 * This method is used to initialise the excel
	 * @param filePath
	 */
	public void initialiseExcelFile(String excelPath)
	{
		FileInputStream fisExcel;
		try {
			fisExcel = new FileInputStream(excelPath);
			workbook = WorkbookFactory.create(fisExcel);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is used to fetch the data from Excel file
	 * @param rowNumber
	 * @param cellNumber
	 * @param SheetName
	 * @return
	 */

	public String getDataFromExcel(int rowNumber,int cellNumber,String SheetName)
	{
		sheet=workbook.getSheet(SheetName);
		return new DataFormatter().formatCellValue(sheet.getRow(rowNumber).getCell(cellNumber));
	}

	/**
	 * This method is used to get the control of sheet,row,cell and type the data
	 */

	public void setDataToExcel(String SheetName, int rowNumber,int cellNumber,String status)
	{
		sheet=workbook.getSheet(SheetName);
		sheet.getRow(rowNumber).createCell(cellNumber).setCellValue(status);
		System.out.println("Data Entered");

	}

	/**
	 * This method is used to get physical file format of the java representative object or open the workbook in write mode
	 * actual writing happens using write method
	 */

	public void writedataToExcel(String excelPath)
	{
		FileOutputStream fosExcel=null;
		try {
			fosExcel = new FileOutputStream(excelPath);
			workbook.write(fosExcel);
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

		}
	}
		
		public void closeWorkbook()
		{
			try {
				workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}



