package org.vtiger.practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateAndDeleteOrganisation {

	public static void main(String[] args) throws IOException 
	{

		//initialise data from Property file
				FileInputStream fis=new FileInputStream("./src/test/resources/commonData.properties");
				Properties properties=new Properties();
				properties.load(fis);
				
				//Generate the random number
				int randomNumber=new Random().nextInt(1000);
				
				//get the control for particular sheet in excel
				FileInputStream fisExcel=new FileInputStream("./src/test/resources/Contacts.xlsx");
				Workbook workbook = WorkbookFactory.create(fisExcel);
				Sheet sheet = workbook.getSheet("Organisation");
				
				//Fetch the data from Property File
				String browser=properties.getProperty("browser");
				String username = properties.getProperty("username");
				String password = properties.getProperty("password");
				String url=properties.getProperty("url");
				String timeout=properties.getProperty("timeout");
				
				//Fetch the data from Excel File
				String expectedOrganisationName = sheet.getRow(2).getCell(1).getStringCellValue()+randomNumber;
				
				//convert string to long
				long longTimeout = Long.parseLong(timeout);
				
				
				//run time polymorphism
				WebDriver driver=null;
				
				//run time polymorphism
				if(browser.equals("chrome"))
				{
					WebDriverManager.chromedriver().setup();
					driver=new ChromeDriver();
				}
				else if(browser.equals("firefox"))
				{
					WebDriverManager.firefoxdriver().setup();
					driver=new FirefoxDriver();
				}
				else if(browser.equals("ie"))
				{
					WebDriverManager.iedriver().setup();
					driver=new InternetExplorerDriver();
				}
				else
				{
					System.out.println("you entered wrong browser key in the property file");
				}
				
				//pre-setting for the browser
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(longTimeout));
				
				WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(longTimeout));
				
					driver.get(url);
					
					driver.findElement(By.name("user_name")).sendKeys(username);
					driver.findElement(By.name("user_password")).sendKeys(password);
					driver.findElement(By.id("submitButton")).click();
					
					
		driver.findElement(By.xpath("//a[text()='Organizations']")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(expectedOrganisationName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[@class='small']"))));
		
		driver.findElement(By.xpath("//a[@class='hdrLink']")).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[.='Go to Advanced Search']"))));
		
		String[] arrPageCount = driver.findElement(By.xpath("//span[@name='Accounts_listViewCountContainerName']")).getText().split(" ");
		String PageCount = arrPageCount[arrPageCount.length-1];
		
		driver.findElement(By.xpath("//input[@class='small']")).clear();
		driver.findElement(By.xpath("//input[@class='small']")).sendKeys(PageCount,Keys.ENTER);
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@id='status']"))));
		
		List<WebElement> listOrganisation = driver.findElements((By.xpath("//table[@class='lvt small']/tbody/tr/td[3]/a")));
		for(int i=0;i<listOrganisation.size();i++)
		{
			String orgName = listOrganisation.get(i).getText();
			if(orgName.equals(expectedOrganisationName)) {
				driver.findElement(By.xpath("//table[@class='lvt small']/tbody/tr["+(i+2)+"]/td[1]/input")).click();
				break;
			}
		}
		
		driver.findElement(By.xpath("//input[@class='crmbutton small delete']")).click();
		
		driver.switchTo().alert().accept();
		
		List<WebElement> listOrganisation2 = driver.findElements(By.xpath("//table[@class='lvt small']/tbody/tr/td[3]/a"));
		int count=0;
		for(int i=0;i<listOrganisation2.size();i++)
		{
			String orgName = listOrganisation2.get(i).getText();
			if(orgName.equals(expectedOrganisationName))
			{
				count++;
				break;
			}
		}
		if(count==0)
		{
			System.out.println("TC Pass");
		}
		driver.quit();
		
	}

}
