package org.vtiger.practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactTestUsingPropertyFile {

	public static void main(String[] args) throws IOException 
	{
		FileInputStream fis=new FileInputStream("./src/test/resources/commonData.properties");
		Properties properties=new Properties();
		properties.load(fis);
		String browser=properties.getProperty("browser");
		String username = properties.getProperty("username");
		String password = properties.getProperty("password");
		String url=properties.getProperty("url");
		String timeout=properties.getProperty("timeout");
		
		long longTimeout = Long.parseLong(timeout);
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
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(longTimeout));
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		Random ran=new Random();
		int randomNumber=ran.nextInt(1000);
		String expectedLastname="Raut"+randomNumber;
		driver.findElement(By.name("lastname")).sendKeys(expectedLastname);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		String actualLastname=driver.findElement(By.id("dtlview_Last Name")).getText();
		if(actualLastname.equals(expectedLastname))
		{
			System.out.println("Contact is Created successfully----->TC is Pass");
		}
		else
		{
			System.out.println("Contact is not Created----->TC is Fail");
		}
		Actions act=new Actions(driver);
		WebElement admistrator = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		act.moveToElement(admistrator).perform();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		driver.quit();



	}

}
