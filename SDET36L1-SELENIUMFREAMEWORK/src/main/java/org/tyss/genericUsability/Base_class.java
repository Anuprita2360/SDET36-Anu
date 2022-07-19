package org.tyss.genericUsability;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.vtiger.ObjectRepository.CommonPage;
import org.vtiger.ObjectRepository.LoginPage;

public class Base_class 
{
	public PropertyFileUsability propertyfileusability;
	public ExcelFileUsability excelfileusability;
	public JavaUsability javausability;
	public SeleniumUsability seleniumusability;
	public int randomNumber;
	public LoginPage loginPage;
	public CommonPage commonPage;
	protected String USERNAME;
	protected String  PASSWORD;
	protected String URL;
	protected String BROWSER;
	protected String timeout;
	//protected long longTimeout;

	public static WebDriver driver;
	
//	@BeforeSuite(alwaysRun = true)
//	public void configBS()
//	{
//		
//	}
	// @Parameters("browser")
	@BeforeClass(alwaysRun = true)
	public void configBC() throws Throwable
	{
		System.out.println ("DB====connect to======");
		propertyfileusability=new PropertyFileUsability();
		excelfileusability=new ExcelFileUsability();
		javausability=new JavaUsability();
		seleniumusability=new SeleniumUsability();

		propertyfileusability.initialisePropertyFile(IConstantUsability.VTIGERPROPERTYFILEPATH);
		excelfileusability.initialiseExcelFile(IConstantUsability.VTIGEREXCELFILEPATH);

		USERNAME=propertyfileusability.getDataFronProperty("username");
		PASSWORD = propertyfileusability.getDataFronProperty("password");
		URL =propertyfileusability.getDataFronProperty("url");
		BROWSER =propertyfileusability.getDataFronProperty("browser");
		timeout=propertyfileusability.getDataFronProperty("timeout");

		System.out.println("==-----------Launch the browser");
		
		//longTimeout = javausability.convertStringToLong(timeout);
		//run time polymorphism
		//seleniumusability.implicitWait(longTimeout);
		driver=seleniumusability.setDriver(BROWSER);
		seleniumusability.maximiseBrowser();
		seleniumusability.initialiseActions();

		loginPage=new LoginPage(driver);
		commonPage = new CommonPage(driver);

		// Navigate to url 
		seleniumusability.openApplication(URL);
	}
	@BeforeMethod(alwaysRun = true)
	public void BeforeMethod() throws Throwable
	{
		//Generate the random number
		randomNumber=javausability.getrandomNumber();
		loginPage.loginAction(USERNAME, PASSWORD);
	}
	@AfterMethod(alwaysRun = true)
	public void AfterMethod()
	{
		commonPage.clickSignOut(seleniumusability);
	}
	@AfterClass(alwaysRun = true)
	public void AfterClass()
	{
		excelfileusability.closeWorkbook();
		System.out .println(" =Close the Browser===");
		seleniumusability.closeBrowser();
	}
	@AfterSuite(alwaysRun = true)
	public void AfterSuite()
	{
		System.out.println("-close DB=");
	}    
}

