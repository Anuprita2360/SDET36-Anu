package org.vtiger.practice;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.tyss.genericUsability.JavaUsability;
import org.tyss.genericUsability.SeleniumUsability;

import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;
public class rmgYantra {
   public static void main(String[] args)throws SQLException
   {
	   JavaUsability javausability=new JavaUsability();
	   SeleniumUsability seleniumusability=new SeleniumUsability();
	   
	   int randomNumber=javausability.getrandomNumber();
	   String expectedProjectName="sdet47"+randomNumber;
	   javausability.printStatement("ExpectedProject");
	   Driver dbDriver = new Driver();
		DriverManager.registerDriver(dbDriver);
		DriverManager.getConnection("jdbc:mysql://localhost:3306/projects","root","root")
		.createStatement().executeUpdate("insert into project(project_id,created_by,created_on,project_name,status,team_size) values('sdet"+randomNumber+"','Nilesh','14/02/2022','"+expectedProjectName+"','Created',22);");
	 
	 
	  WebDriver driver=seleniumusability.setDriver("chrome");
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	  driver.get("http://localhost:8084/");
	  driver.findElement(By.id("usernmae")).sendKeys("rmgyantra");
	  driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
	  driver.findElement(By.xpath("//button[text()='Sign in']")).click();
	  driver.findElement(By.xpath("//a[text()='Projects']")).click();
	  List<WebElement> listOfProjects = driver.findElements(By.xpath("//table[@class='table table-striped table-hover']/tbody/tr/td[2]"));
	 for(WebElement project:listOfProjects)
	 {
		 String actualProjectName=project.getText();
		 if(actualProjectName.equals(expectedProjectName))
		 {
			 System.out.println("project is present in the list of projects page");
			 System.out.println("ActualProjectName--->"+actualProjectName);
			 break;
		 }
	 }
	  
	  driver.quit();
	  
	}

}
