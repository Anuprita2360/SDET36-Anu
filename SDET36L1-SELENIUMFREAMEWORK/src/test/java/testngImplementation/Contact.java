package testngImplementation;

import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;	
import org.testng.annotations.Test;
import org.tyss.genericUsability.Base_class;
import org.tyss.genericUsability.IConstantUsability;

public class Contact extends Base_class
{
	@Test
	public void contactModule_Test()
	{
		//Fetch the data from Excel File
		String SheetName="Contacts";
		String ExpectedContactLastName = excelfileusability.getDataFromExcel(2, 1, SheetName)+randomNumber;
		javausability.printStatement(ExpectedContactLastName);

		//convert string to long
		long longTimeout =javausability.convertStringToLong(timeout);

		seleniumusability.implicitWait(longTimeout);

		//create contact
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		String expectedLastname="Raut"+randomNumber;
		driver.findElement(By.name("lastname")).sendKeys(expectedLastname);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		//fetch the actual contact last name
		String actualLastname=driver.findElement(By.id("dtlview_Last Name")).getText();

		//validating contact last name

		if(actualLastname.equals(expectedLastname))
		{
			javausability.printStatement("Contact is Created successfully----->TC is Pass");
			excelfileusability.setDataToExcel(SheetName, 2, 3, IConstantUsability.VTIGERSTATUS1);
			excelfileusability.writedataToExcel(IConstantUsability.VTIGEREXCELFILEPATH);
		}
		else
		{
			javausability.printStatement("Contact is not Created----->TC is Fail");
			excelfileusability.setDataToExcel(SheetName, 2, 3, IConstantUsability.VTIGERSTATUS2);
			excelfileusability.writedataToExcel(IConstantUsability.VTIGEREXCELFILEPATH);
		}

	}
	
	@Test
	
	public void createcontactwitOrganisation_test() throws InterruptedException
	{

		
		//Fetch the data from Excel File
		String SheetName1="Contacts";
		String SheetName2 = "Organisation";
		String ExpectedContactLastName = excelfileusability.getDataFromExcel(4, 2, SheetName1)+randomNumber;
		String expectedOrganisationName = excelfileusability.getDataFromExcel(4, 1, SheetName2)+randomNumber;


		javausability.printStatement(ExpectedContactLastName);
		javausability.printStatement(expectedOrganisationName);

		//convert string to long
		long longTimeout = javausability.convertStringToLong(timeout);

		seleniumusability.implicitWait(longTimeout);

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

	}

}

