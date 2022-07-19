package org.vtiger.campaigns;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
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

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateCampaignTestUsingPropertyAndExcelFile {



	public static void main(String[] args) throws IOException 
	{


		//Creating the objects
		PropertyFileUsability propertyfileusability=new PropertyFileUsability();
		ExcelFileUsability excelfileusability=new ExcelFileUsability();
		JavaUsability javausability=new JavaUsability();
		SeleniumUsability seleniumusability=new SeleniumUsability();

		//initialise data from Property file
		propertyfileusability.initialisePropertyFile(IConstantUsability.VTIGERPROPERTYFILEPATH);

		//Generate the random number
		int randomNumber=javausability.getrandomNumber();

		//get the control for particular sheet in excel
		excelfileusability.initialiseExcelFile(IConstantUsability.VTIGEREXCELFILEPATH);

		//Fetch the data from Property File
		String browser=propertyfileusability.getDataFronProperty("browser");
		String username = propertyfileusability.getDataFronProperty("username");
		String password = propertyfileusability.getDataFronProperty("password");
		String url=propertyfileusability.getDataFronProperty("url");
		String timeout=propertyfileusability.getDataFronProperty("timeout");

		String SheetName = "Campaigns";
		//Fetch the data from Excel File
		String expectedCampaignname = excelfileusability.getDataFromExcel(2, 1, SheetName)+randomNumber;

		//convert string to long
		long longTimeout = javausability.convertStringToLong(timeout);

		//run time polymorphism
		WebDriver driver=seleniumusability.setDriver(browser);

		seleniumusability.maximiseBrowser();
		seleniumusability.implicitWait(longTimeout);

		seleniumusability.initialiseActions();

		//create object for POM Classes

		LoginPage loginPage=new LoginPage(driver);
		CommonPage commonPage=new CommonPage(driver);
		CampaignPage campaignpage=new CampaignPage(driver);
		CreateNewCampaignPage createNewCampaignPage=new CreateNewCampaignPage(driver);
		CampaignInformationpage campaignInformationPage=new CampaignInformationpage(driver);


		seleniumusability.openApplication(url);
		loginPage.loginAction(username, password);

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
		excelfileusability.closeWorkbook();
		commonPage.clickSignOut(seleniumusability);
		seleniumusability.closeBrowser();


	}

}
