package org.vtiger.contacts;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.tyss.genericUsability.ExcelFileUsability;
import org.tyss.genericUsability.IConstantUsability;
import org.tyss.genericUsability.JavaUsability;
import org.tyss.genericUsability.PropertyFileUsability;
import org.tyss.genericUsability.SeleniumUsability;

public class CreateContactTestusingExcelAndPropertyFile {
	
	static WebDriver driver;

	public static void main(String[] args) throws IOException 
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
		String url=propertyfileusability.getDataFronProperty("url");
		String browser=propertyfileusability.getDataFronProperty("browser");
		String username = propertyfileusability.getDataFronProperty("username");
		String password = propertyfileusability.getDataFronProperty("password");
		String timeout=propertyfileusability.getDataFronProperty("timeout");
		
		//Fetch the data from Excel File
		String SheetName="Contacts";
		String ExpectedContactLastName = excelfileusability.getDataFromExcel(2, 1, SheetName)+randomNumber;
		javausability.printStatement(ExpectedContactLastName);
		
		//convert string to long
		long longTimeout =javausability.convertStringToLong(timeout);
		
		//launching the browser in run time based on browser key
		WebDriver driver=seleniumusability.setDriver(browser);
		
		//pre-setting for the browser
		seleniumusability.maximiseBrowser();
		seleniumusability.implicitWait(longTimeout);
		
		//Create Object for POM classes
		
		
		//navigating the application
		seleniumusability.openApplication(url);
		
		//login to the app
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
		//create contact
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		String expectedLastname="Raut"+randomNumber;
		driver.findElement(By.name("lastname")).sendKeys(expectedLastname);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//fetch the actual contact last name
		String actualLastname=driver.findElement(By.id("dtlview_Last Name")).getText();
		
		//validating contact last name
		
		if(actualLastname.equals(expectedLastname))
		{
			javausability.printStatement("Contact is Created successfully----->TC is Pass");
			excelfileusability.setDataToExcel(SheetName, 2, 3, IConstantUsability.VTIGERSTATUS1);
			excelfileusability.writedataToExcel(IConstantUsability.VTIGEREXCELFILEPATH);
		}
		else
		{
			javausability.printStatement("Contact is not Created----->TC is Fail");
			excelfileusability.setDataToExcel(SheetName, 2, 3, IConstantUsability.VTIGERSTATUS2);
			excelfileusability.writedataToExcel(IConstantUsability.VTIGEREXCELFILEPATH);
		}
		
		//logout from the application
		seleniumusability.initialiseActions();
		WebElement admistrator = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		seleniumusability.mouseHoverOnElement(admistrator);
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		System.out.println("TC Pass"+"Contact");
		System.out.println("dsjahdj");
		
		//close the browser
		seleniumusability.closeBrowser();
		
	}

}
