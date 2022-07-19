package org.vtiger.products;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.tyss.genericUsability.ExcelFileUsability;
import org.tyss.genericUsability.IConstantUsability;
import org.tyss.genericUsability.JavaUsability;
import org.tyss.genericUsability.PropertyFileUsability;
import org.tyss.genericUsability.SeleniumUsability;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateProductTest {

	public static void main(String[] args) throws IOException, InterruptedException 
	{
		//Creating the objects
				PropertyFileUsability propertyfileusability=new PropertyFileUsability();
				ExcelFileUsability excelfileusability=new ExcelFileUsability();
				JavaUsability javausability=new JavaUsability();
				SeleniumUsability seleniumusability=new SeleniumUsability();

				//initialise data from Property file
				propertyfileusability.initialisePropertyFile(IConstantUsability.VTIGERPROPERTYFILEPATH);
				
				//Generate the random number
				int randomNumber=javausability.getrandomNumber();
				
				//get the control for particular sheet in excel
				excelfileusability.initialiseExcelFile(IConstantUsability.VTIGEREXCELFILEPATH);
				
				//Fetch the data from Property File
				String browser=propertyfileusability.getDataFronProperty("browser");
				String username = propertyfileusability.getDataFronProperty("username");
				String password = propertyfileusability.getDataFronProperty("password");
				String url=propertyfileusability.getDataFronProperty("url");
				String timeout=propertyfileusability.getDataFronProperty("timeout");
				
				//Fetch the data from Excel File
				String SheetName="Products";
				String expectedProductName = excelfileusability.getDataFromExcel(2, 1, SheetName)+randomNumber;
				
				//convert string to long
				long longTimeout = javausability.convertStringToLong(timeout);
				
				

		//run time polymorphism
		WebDriver driver=seleniumusability.setDriver(browser);
		
		seleniumusability.maximiseBrowser();
		seleniumusability.implicitWait(longTimeout);
		
		seleniumusability.openApplication(url);
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();


		driver.findElement(By.xpath("//a[text()='Products']")).click();
		driver.findElement(By.xpath("//img[@title='Create Product...']")).click();
		driver.findElement(By.name("productname")).sendKeys(expectedProductName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String actualProductName = driver.findElement(By.xpath("//span[@id='dtlview_Product Name']")).getText();
		if(actualProductName.equals(expectedProductName))
		{
			javausability.printStatement("product created successfully------->TC pass");
			excelfileusability.setDataToExcel(SheetName, 2, 2, IConstantUsability.VTIGERSTATUS1);
			excelfileusability.writedataToExcel(IConstantUsability.VTIGEREXCELFILEPATH);
		}
		else
		{
			javausability.printStatement("product not created ------->TC fail");
			excelfileusability.setDataToExcel(SheetName, 2, 2, IConstantUsability.VTIGERSTATUS2);
			excelfileusability.writedataToExcel(IConstantUsability.VTIGEREXCELFILEPATH);
			
		}
		excelfileusability.closeWorkbook();
		seleniumusability.initialiseActions();
		WebElement admistrator = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		seleniumusability.mouseHoverOnElement(admistrator);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();

		//close the browser
		seleniumusability.closeBrowser();

	}

}
