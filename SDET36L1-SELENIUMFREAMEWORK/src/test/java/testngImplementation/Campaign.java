package testngImplementation;

import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.tyss.genericUsability.Base_class;
import org.tyss.genericUsability.ExcelFileUsability;
import org.tyss.genericUsability.IConstantUsability;
import org.tyss.genericUsability.JavaUsability;
import org.tyss.genericUsability.PropertyFileUsability;
import org.tyss.genericUsability.SeleniumUsability;
import org.vtiger.ObjectRepository.CampaignInformationpage;
import org.vtiger.ObjectRepository.CampaignPage;
import org.vtiger.ObjectRepository.CommonPage;
import org.vtiger.ObjectRepository.CreateNewCampaignPage;
import org.vtiger.ObjectRepository.LoginPage;

public class Campaign extends Base_class
{
	@Test
	public void campaignmodule_Test()
	{
        String SheetName = "Campaigns";
		//Fetch the data from Excel File
		String expectedCampaignname = excelfileusability.getDataFromExcel(2, 1, SheetName)+randomNumber;
		//create object for POM Classes
		CampaignPage campaignpage=new CampaignPage(driver);
		CreateNewCampaignPage createNewCampaignPage=new CreateNewCampaignPage(driver);
		CampaignInformationpage campaignInformationPage=new CampaignInformationpage(driver);

		//commonPage.clickMore(seleniumusability);
		commonPage.clickMore(seleniumusability);
		commonPage.clickCampaign();
		campaignpage.clickOnCreateCampaignBtn();
		createNewCampaignPage.createCampaign(expectedCampaignname);

		String actualCampaignname=campaignInformationPage.getActualCampaignName();
		if(actualCampaignname.equals(expectedCampaignname))
		{
			javausability.printStatement("Campaign Name is Created----->TC Pass");
			excelfileusability.setDataToExcel(SheetName, 2, 3, IConstantUsability.VTIGERSTATUS1);
			excelfileusability.writedataToExcel(IConstantUsability.VTIGEREXCELFILEPATH);

		}
		else
		{
			javausability.printStatement("Campaign Name is not Created----->TC fail");
			excelfileusability.setDataToExcel(SheetName, 2, 3, IConstantUsability.VTIGERSTATUS2);
			excelfileusability.writedataToExcel(IConstantUsability.VTIGEREXCELFILEPATH);

		}
		
	}
	@Test
	public void createcampaignwithProduct_test() throws InterruptedException
	{
		//Fetch the data from Excel File
		String SheetName1="Campaigns";
		String SheetName2="Products";
		String expectedCampaignname = excelfileusability.getDataFromExcel(2, 1,SheetName1 )+randomNumber;
		String expectedProductName = excelfileusability.getDataFromExcel(2, 1, SheetName2)+randomNumber; 
		
		driver.findElement(By.xpath("//a[text()='Products']")).click();
		driver.findElement(By.xpath("//img[@title='Create Product...']")).click();
		driver.findElement(By.name("productname")).sendKeys(expectedProductName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		WebElement More = driver.findElement(By.xpath("//a[text()='More']"));
		seleniumusability.mouseHoverOnElement(More);
		driver.findElement(By.name("Campaigns")).click();
		driver.findElement(By.xpath("//img[@alt='Create Campaign...']")).click();
		driver.findElement(By.name("campaignname")).sendKeys(expectedCampaignname);

		driver.findElement(By.xpath("//img[@src='themes/softed/images/select.gif']")).click();
		
		Set<String> winIds= driver.getWindowHandles();

		for(String Id:winIds)
		{
			driver.switchTo().window(Id);
			if(driver.getCurrentUrl().contains("Products"))
			{
				break;
			}
		}
		driver.findElement(By.id("search_txt")).sendKeys(expectedProductName);
		Thread.sleep(2000);
		driver.findElement(By.name("search")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[.='"+expectedProductName+"']")).click();
		//driver.switchTo().window(wh);

		Set<String> winIds1= driver.getWindowHandles();

		for(String Id:winIds1)
		{
			driver.switchTo().window(Id);
			if(driver.getCurrentUrl().contains("Campaigns"))
			{
				break;
			}
		}

		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		String actualCampaignname=driver.findElement(By.xpath("//span[@id='dtlview_Campaign Name']")).getText();
		String actualProductName = driver.findElement(By.xpath("//span[@id='dtlview_Product']")).getText();
		javausability.printStatement(actualCampaignname);
		if(actualCampaignname.equals(expectedCampaignname) && actualProductName.equals(expectedProductName))
		{
			javausability.printStatement("Campaign Name with product is Created----->TC Pass");
			excelfileusability.setDataToExcel(SheetName1, 4, 3, IConstantUsability.VTIGERSTATUS1);
			excelfileusability.writedataToExcel(IConstantUsability.VTIGEREXCELFILEPATH);
			
		}
		else
		{
			javausability.printStatement("Campaign Name with product is not Created----->TC fail");
			excelfileusability.setDataToExcel(SheetName1, 4, 3, IConstantUsability.VTIGERSTATUS2);
			excelfileusability.writedataToExcel(IConstantUsability.VTIGEREXCELFILEPATH);
			
		}
	    
//	    Assert.assertEquals(actualCampaignname, expectedCampaignname);
		//Assert.assertEquals(actualProductName, expectedProductName);
	}
}
