package org.vtiger.contacts;

import java.io.IOException;
import java.time.Duration;
import java.util.Random;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.tyss.genericUsability.ExcelFileUsability;
import org.tyss.genericUsability.IConstantUsability;
import org.tyss.genericUsability.JavaUsability;
import org.tyss.genericUsability.PropertyFileUsability;
import org.tyss.genericUsability.SeleniumUsability;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactWithOrganisationTest {

	public static void main(String[] args) throws InterruptedException, EncryptedDocumentException, IOException 
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
		String SheetName1="Contacts";
		String SheetName2 = "Organisation";
		String ExpectedContactLastName = excelfileusability.getDataFromExcel(4, 2, SheetName1)+randomNumber;
		String expectedOrganisationName = excelfileusability.getDataFromExcel(4, 1, SheetName2)+randomNumber;


		javausability.printStatement(ExpectedContactLastName);
		javausability.printStatement(expectedOrganisationName);

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
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		String expectedLastname="Raut"+new Random().nextInt(100);
		driver.findElement(By.name("lastname")).sendKeys(expectedLastname);
		driver.findElement(By.xpath("//table[@class='small']/tbody/tr[5]/td[2]/img[@src='themes/softed/images/select.gif']")).click();


		//	String wh = driver.getWindowHandle();
		Set<String> winIds= driver.getWindowHandles();

		for(String Id:winIds)
		{
			driver.switchTo().window(Id);
			if(driver.getCurrentUrl().contains("Accounts"))
			{
				break;
			}
		}
		/*Iterator<String> it = cw.iterator();

		while(it.hasNext())
		{
			String str = it.next();
			driver.switchTo().window(str);
			Thread.sleep(2000);
			String currentwindow = driver.getTitle();
			System.out.println(currentwindow);
			if(wh.contains(currentwindow))
			{
				break;
			}
		}*/

		driver.findElement(By.id("search_txt")).sendKeys(expectedOrganisationName);
		Thread.sleep(2000);
		driver.findElement(By.name("search")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[text()='"+expectedOrganisationName+"']")).click();  //dynamic XPATH
		Thread.sleep(3000);
		Set<String> winIds1= driver.getWindowHandles();

		for(String Id:winIds1)
		{
			driver.switchTo().window(Id);
			if(driver.getCurrentUrl().contains("Contacts"))
			{
				break;
			}
		}


		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		String actualOrganisationName = driver.findElement(By.xpath("//td[text()='Organization Name']/following-sibling::td/a")).getText();
		String actualLastname=driver.findElement(By.id("dtlview_Last Name")).getText();
		javausability.printStatement(actualOrganisationName);
		if(actualOrganisationName.equals(expectedOrganisationName)  && actualLastname.equals(expectedLastname))
		{
			javausability.printStatement("Contact is created with Organisation-------TC pass");
			excelfileusability.setDataToExcel(SheetName1, 4, 3, IConstantUsability.VTIGERSTATUS1);
			excelfileusability.writedataToExcel(IConstantUsability.VTIGEREXCELFILEPATH);

		}
		else
		{
			javausability.printStatement("Contact is not created with Organisation-------TC fail");
			excelfileusability.setDataToExcel(SheetName1, 4, 3, IConstantUsability.VTIGERSTATUS2);
			excelfileusability.writedataToExcel(IConstantUsability.VTIGEREXCELFILEPATH);

		}

		//logout from the application
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
