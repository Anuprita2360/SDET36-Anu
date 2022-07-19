package org.vtiger.campaigns;

import java.io.IOException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.tyss.genericUsability.ExcelFileUsability;
import org.tyss.genericUsability.IConstantUsability;
import org.tyss.genericUsability.JavaUsability;
import org.tyss.genericUsability.PropertyFileUsability;
import org.tyss.genericUsability.SeleniumUsability;
import org.vtiger.ObjectRepository.LoginPage;

public class CreateCampaignWithProductTest {

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
		String SheetName1="Campaigns";
		String SheetName2="Products";
		String expectedCampaignname = excelfileusability.getDataFromExcel(2, 1,SheetName1 )+randomNumber;
		String expectedProductName = excelfileusability.getDataFromExcel(2, 1, SheetName2)+randomNumber;

		//convert string to long
		long longTimeout = javausability.convertStringToLong(timeout);


		//run time polymorphism
	
		WebDriver driver=seleniumusability.setDriver(browser);
		
		seleniumusability.maximiseBrowser();
		seleniumusability.implicitWait(longTimeout);
		
		//Create Object for POM classes
		LoginPage loginPage=new LoginPage(driver);
		
		seleniumusability.openApplication(url);
		loginPage.loginAction(username, password);

		driver.findElement(By.xpath("//a[text()='Products']")).click();
		driver.findElement(By.xpath("//img[@title='Create Product...']")).click();
		driver.findElement(By.name("productname")).sendKeys(expectedProductName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		seleniumusability.initialiseActions();
		WebElement More = driver.findElement(By.xpath("//a[text()='More']"));
		seleniumusability.mouseHoverOnElement(More);
		driver.findElement(By.name("Campaigns")).click();
		driver.findElement(By.xpath("//img[@alt='Create Campaign...']")).click();
		driver.findElement(By.name("campaignname")).sendKeys(expectedCampaignname);

		driver.findElement(By.xpath("//img[@src='themes/softed/images/select.gif']")).click();
		/*String wh = driver.getWindowHandle();
		Set<String> cw= driver.getWindowHandles();
		Iterator<String> it = cw.iterator();

		while(it.hasNext())
		{
			String str = it.next();
			driver.switchTo().window(str);
			Thread.sleep(2000);
			String currentwindow = driver.getTitle();
			if(wh.contains(currentwindow))
			{
				break;
			}
		}*/
		Set<String> winIds= driver.getWindowHandles();

		for(String Id:winIds)
		{
			driver.switchTo().window(Id);
			if(driver.getCurrentUrl().contains("Products"))
			{
				break;
			}
		}
		driver.findElement(By.id("search_txt")).sendKeys(expectedProductName);
		Thread.sleep(2000);
		driver.findElement(By.name("search")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[.='"+expectedProductName+"']")).click();
		//driver.switchTo().window(wh);

		Set<String> winIds1= driver.getWindowHandles();

		for(String Id:winIds1)
		{
			driver.switchTo().window(Id);
			if(driver.getCurrentUrl().contains("Campaigns"))
			{
				break;
			}
		}

		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		String actualCampaignname=driver.findElement(By.xpath("//span[@id='dtlview_Campaign Name']")).getText();
		String actualProductName = driver.findElement(By.xpath("//span[@id='dtlview_Product']")).getText();
		javausability.printStatement(actualCampaignname);
		if(actualCampaignname.equals(expectedCampaignname) && actualProductName.equals(expectedProductName))
		{
			javausability.printStatement("Campaign Name with product is Created----->TC Pass");
			excelfileusability.setDataToExcel(SheetName1, 4, 3, IConstantUsability.VTIGERSTATUS1);
			excelfileusability.writedataToExcel(IConstantUsability.VTIGEREXCELFILEPATH);
			
		}
		else
		{
			javausability.printStatement("Campaign Name with product is not Created----->TC fail");
			excelfileusability.setDataToExcel(SheetName1, 4, 3, IConstantUsability.VTIGERSTATUS2);
			excelfileusability.writedataToExcel(IConstantUsability.VTIGEREXCELFILEPATH);
			
		}
		excelfileusability.closeWorkbook();
		WebElement admistrator = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		seleniumusability.mouseHoverOnElement(admistrator);
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		seleniumusability.closeBrowser();

	}
}
