package org.vtiger.organizations;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.tyss.genericUsability.ExcelFileUsability;
import org.tyss.genericUsability.IConstantUsability;
import org.tyss.genericUsability.JavaUsability;
import org.tyss.genericUsability.PropertyFileUsability;
import org.tyss.genericUsability.SeleniumUsability;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganisationwithIndustryAndTypeTest 
{
	static WebElement industry;
	public static void main(String[] args) throws InterruptedException, IOException 
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
		String SheetName="Organisation";
		String expectedOrganisationName = excelfileusability.getDataFromExcel(2, 1, SheetName)+randomNumber;
		
		//convert string to long
		long longTimeout = javausability.convertStringToLong(timeout);
		
		WebDriver driver=seleniumusability.setDriver(browser);
		//pre-setting for the browser
		seleniumusability.maximiseBrowser();
		seleniumusability.implicitWait(longTimeout);

		//navigating the application
		seleniumusability.openApplication(url);

		//login to the app
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();

		driver.findElement(By.xpath("//table[@class='hdrTabBg']/tbody/tr/child::td[@class='small']/table/tbody/tr/td/following-sibling::td/a[text()='Organizations']")).click();
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();

		driver.findElement(By.name("accountname")).sendKeys(expectedOrganisationName);
		WebElement IndustryDropDown = driver.findElement(By.xpath("//select[@name='industry']"));
		Select selectIndustry=new Select(IndustryDropDown);
		List<WebElement> options = selectIndustry.getOptions();
		int count=options.size();
		String text = null;
		for(int i=0;i<count;i++)
		{
			selectIndustry.selectByIndex(8);
			text = options.get(8).getText();
		
		}
		
		
		WebElement Type = driver.findElement(By.xpath("//select[@name='accounttype']"));
		Select selectType=new Select(Type);
		List<WebElement> typeOptions = selectType.getOptions(); // no need to use getOptions() and FOR LOOP directly do with selectByValue() method
		int count1=typeOptions.size();
		String text1=null;
		for(int j=0;j<count1;j++)
		{
			selectType.selectByIndex(7);
			text1 = typeOptions.get(7).getText();
		
		}
		
		driver.findElement(By.xpath("//input[@name='assigntype'][2]")).click();
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String actualOrganisationName = driver.findElement(By.id("dtlview_Organization Name")).getText();
		
		String expectedIndustry = driver.findElement(By.xpath("//span/font[text()='Education']")).getText();
		
		String expectedType = driver.findElement(By.xpath("//span/font[text()='Press']")).getText();
		
		if(actualOrganisationName.equals(expectedOrganisationName) && text.equals(expectedIndustry) &&  text1.equals(expectedType))  //we can use && operator to validate industry and type in the same IF BLOCK
		{
			javausability.printStatement("Organisation is created with Industry and type-------TC pass");
			excelfileusability.setDataToExcel(SheetName, 4, 4, IConstantUsability.VTIGERSTATUS1);
			excelfileusability.writedataToExcel(IConstantUsability.VTIGEREXCELFILEPATH);
			
		}
		else
		{
			javausability.printStatement("Organisation is not created with Industry and Type-------TC fail");
			excelfileusability.setDataToExcel(SheetName, 4, 4, IConstantUsability.VTIGERSTATUS2);
			excelfileusability.writedataToExcel(IConstantUsability.VTIGEREXCELFILEPATH);
			
		}
		
		excelfileusability.closeWorkbook();
		seleniumusability.initialiseActions();
		WebElement admistrator = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		seleniumusability.mouseHoverOnElement(admistrator);
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		seleniumusability.closeBrowser();


	}

}
