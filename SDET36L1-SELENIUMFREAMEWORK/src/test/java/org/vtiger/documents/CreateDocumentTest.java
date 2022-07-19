package org.vtiger.documents;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateDocumentTest {

	public static void main(String[] args) throws IOException, InterruptedException, AWTException 
	{
		FileInputStream fis=new FileInputStream("./src/test/resources/commonData.properties");
		Properties properties=new Properties();
		properties.load(fis);
		
		//Generate the random number
		int randomNumber=new Random().nextInt(1000);
		
		String browser=properties.getProperty("browser");
		String username = properties.getProperty("username");
		String password = properties.getProperty("password");
		String url=properties.getProperty("url");
		String timeout=properties.getProperty("timeout");
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get(url);
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
		driver.findElement(By.xpath("//a[text()='Documents']")).click();
		driver.findElement(By.xpath("//img[@title='Create Document...']")).click();
		Thread.sleep(2000);
		String title = "Documents"+randomNumber;
		driver.findElement(By.xpath("//input[@name=\"notes_title\"]")).sendKeys(title);
		driver.findElement(By.xpath("//iframe[@title='Rich text editor, notecontent, press ALT 0 for help.']")).sendKeys(title);
		Robot robot=new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_A);
		robot.keyPress(KeyEvent.VK_B);
		robot.keyPress(KeyEvent.VK_I);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String actualDescription = driver.findElement(By.xpath("//td[@class=\"dvtCellInfo\"]/p/em/strong[.='"+title+"']")).getText();
		if(actualDescription.equals(title))
		{
			System.out.println("TC is Pass");
		}
		else
		{
			System.out.println();
		}
		driver.quit();

	}

}
