package org.vtiger.practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactTest 
{

	public static void main(String[] args)
	{
		//System.setProperty("webdriver.chrome.driver","./driver/chromedriver.exe");
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("http://localhost:8888/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		String expectedLastname="Raut";
		driver.findElement(By.name("lastname")).sendKeys(expectedLastname);
		driver.findElement(By.name("button")).click();
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
