package org.vtiger.practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateCampaignsTest
{
	public static void main(String[] args) 
	{
		//System.setProperty("webdriver.chrome.driver","./src/main/resources/chromedriver.exe");
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("http://localhost:8888/");
		driver.manage().window().maximize();
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();

		Actions act=new Actions(driver);
		WebElement More = driver.findElement(By.xpath("//a[text()='More']"));
		act.moveToElement(More).perform();
		driver.findElement(By.name("Campaigns")).click();
		driver.findElement(By.xpath("//img[@alt='Create Campaign...']")).click();
		String expectedCampaignname="Raut";
		driver.findElement(By.name("campaignname")).sendKeys(expectedCampaignname);
		driver.findElement(By.name("button")).click();

		String actualCampaignname=driver.findElement(By.xpath("//td/span[text()='Raut']")).getText();
		if(actualCampaignname.equals(expectedCampaignname))
		{
			System.out.println("Campaign Name is Created----->TC Pass");
		}
		else
		{
			System.out.println("Campaign Name is not Created----->TC fail");
		}
		WebElement admistrator = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		act.moveToElement(admistrator).perform();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		driver.quit();


	}
}
