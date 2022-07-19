package org.vtiger.practice;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ValidateGUIwrtDataBase {

	public static void main(String[] args) throws SQLException 
	{
		 WebDriverManager.chromedriver().setup();
		  WebDriver driver=new ChromeDriver();
		  
		  driver.get("http://localhost:8084/");
		  Random ran=new Random();
		  int randomNumber=ran.nextInt(1000);
		  String projectName="sdet48"+randomNumber;
		  System.out.println("Project Name--->"+projectName);
		  driver.manage().window().maximize();
		  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		  driver.findElement(By.id("usernmae")).sendKeys("rmgyantra");
		  driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		  driver.findElement(By.xpath("//button[text()='Sign in']")).click();
		  System.out.println("successfully login");
		  
		  driver.findElement(By.xpath("//a[text()='Projects']")).click();
		  driver.findElement(By.xpath("//span[text()='Create Project']")).click();
		  driver.findElement(By.name("projectName")).sendKeys(projectName);
		  driver.findElement(By.name("createdBy")).sendKeys("nilima");
		  WebElement projectstatusdropdown = driver.findElement(By.xpath("//label[.='Project Status ']/following-sibling::select"));
		  Select select=new Select(projectstatusdropdown);
		  select.selectByVisibleText("Completed");
		  
		  driver.findElement(By.xpath("//input[@value='Add Project']")).click();
		  
		  // connection with the data base 
		  Driver dbDriver=new Driver();
		  DriverManager.registerDriver(dbDriver);
		  ResultSet result = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root").createStatement().executeQuery("select * from project;");
		  while(result.next())
		  {
			  if(result.getString("project_name").equals(projectName))
      		  {
				  System.out.println("Project is present in database");
				  System.out.println("Actual project Name--->"+result.getString("project_name"));
			  }
		  }
		  
         driver.quit();
	}

}
