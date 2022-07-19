package testngImplementation;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.tyss.genericUsability.Base_class;
import org.tyss.genericUsability.ExcelFileUsability;
import org.tyss.genericUsability.IConstantUsability;
import org.tyss.genericUsability.JavaUsability;
import org.tyss.genericUsability.PropertyFileUsability;
import org.tyss.genericUsability.SeleniumUsability;

public class Organisation extends Base_class
{
	@Test
	
	public void organisation_test()
	{
				//Fetch the data from Excel File
				String SheetName="Organisation";
				String expectedOrganisationName = excelfileusability.getDataFromExcel(2, 1, SheetName)+randomNumber;

				//convert string to long
				long longTimeout = javausability.convertStringToLong(timeout);

				seleniumusability.implicitWait(longTimeout);

				driver.findElement(By.xpath("//table[@class='hdrTabBg']/tbody/tr/child::td[@class='small']/table/tbody/tr/td/following-sibling::td/a[text()='Organizations']")).click();
				driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();

				driver.findElement(By.name("accountname")).sendKeys(expectedOrganisationName);
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				String actualOrganisationName = driver.findElement(By.id("dtlview_Organization Name")).getText();
				System.out.println(actualOrganisationName);
				if(actualOrganisationName.equals(expectedOrganisationName))
				{
					javausability.printStatement("Organisation is created-------TC pass");
					excelfileusability.setDataToExcel(SheetName, 2, 3, IConstantUsability.VTIGERSTATUS1);
					excelfileusability.writedataToExcel(IConstantUsability.VTIGEREXCELFILEPATH);
					javausability.printStatement("Data Entered");
				}
				else
				{
					javausability.printStatement("Organisation is not created-------TC fail");
					excelfileusability.setDataToExcel(SheetName, 2, 3, IConstantUsability.VTIGERSTATUS2);
					excelfileusability.writedataToExcel(IConstantUsability.VTIGEREXCELFILEPATH);
					javausability.printStatement("Data Entered");
				}		
	}
	
	@Test
	
	public void organisationwithIndustryAndType_test()
	{
		
				//Fetch the data from Excel File
				String SheetName="Organisation";
				String expectedOrganisationName = excelfileusability.getDataFromExcel(2, 1, SheetName)+randomNumber;
				
				//convert string to long
				long longTimeout = javausability.convertStringToLong(timeout);
			
				seleniumusability.implicitWait(longTimeout);

				driver.findElement(By.xpath("//table[@class='hdrTabBg']/tbody/tr/child::td[@class='small']/table/tbody/tr/td/following-sibling::td/a[text()='Organizations']")).click();
				driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();

				driver.findElement(By.name("accountname")).sendKeys(expectedOrganisationName);
				WebElement IndustryDropDown = driver.findElement(By.xpath("//select[@name='industry']"));
				Select selectIndustry=new Select(IndustryDropDown);
				List<WebElement> options = selectIndustry.getOptions();
				int count=options.size();
				String text = null;
				for(int i=0;i<count;i++)
				{
					selectIndustry.selectByIndex(8);
					text = options.get(8).getText();
				
				}
				
				WebElement Type = driver.findElement(By.xpath("//select[@name='accounttype']"));
				Select selectType=new Select(Type);
				List<WebElement> typeOptions = selectType.getOptions(); // no need to use getOptions() and FOR LOOP directly do with selectByValue() method
				int count1=typeOptions.size();
				String text1=null;
				for(int j=0;j<count1;j++)
				{
					selectType.selectByIndex(7);
					text1 = typeOptions.get(7).getText();
				
				}
				
				driver.findElement(By.xpath("//input[@name='assigntype'][2]")).click();
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				String actualOrganisationName = driver.findElement(By.id("dtlview_Organization Name")).getText();
				
				String expectedIndustry = driver.findElement(By.xpath("//span/font[text()='Education']")).getText();
				
				String expectedType = driver.findElement(By.xpath("//span/font[text()='Press']")).getText();
				
				if(actualOrganisationName.equals(expectedOrganisationName) && text.equals(expectedIndustry) &&  text1.equals(expectedType))  //we can use && operator to validate industry and type in the same IF BLOCK
				{
					javausability.printStatement("Organisation is created with Industry and type-------TC pass");
					excelfileusability.setDataToExcel(SheetName, 4, 4, IConstantUsability.VTIGERSTATUS1);
					excelfileusability.writedataToExcel(IConstantUsability.VTIGEREXCELFILEPATH);
					
				}
				else
				{
					javausability.printStatement("Organisation is not created with Industry and Type-------TC fail");
					excelfileusability.setDataToExcel(SheetName, 4, 4, IConstantUsability.VTIGERSTATUS2);
					excelfileusability.writedataToExcel(IConstantUsability.VTIGEREXCELFILEPATH);
					
				}

	}

}
