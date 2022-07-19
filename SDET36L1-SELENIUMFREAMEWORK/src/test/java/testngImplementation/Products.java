package testngImplementation;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.tyss.genericUsability.Base_class;
import org.tyss.genericUsability.IConstantUsability;

public class Products extends Base_class
{
	@Test

	public void products_test()
	{

		//Fetch the data from Excel File
		String SheetName="Products";
		String expectedProductName = excelfileusability.getDataFromExcel(2, 1, SheetName)+randomNumber;

		//convert string to long
		long longTimeout = javausability.convertStringToLong(timeout);

		seleniumusability.implicitWait(longTimeout);

		driver.findElement(By.xpath("//a[text()='Products']")).click();
		driver.findElement(By.xpath("//img[@title='Create Product...']")).click();
		driver.findElement(By.name("productname")).sendKeys(expectedProductName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String actualProductName = driver.findElement(By.xpath("//span[@id='dtlview_Product Name']")).getText();
		if(actualProductName.equals(expectedProductName))
		{
			javausability.printStatement("product created successfully------->TC pass");
			excelfileusability.setDataToExcel(SheetName, 2, 2, IConstantUsability.VTIGERSTATUS1);
			excelfileusability.writedataToExcel(IConstantUsability.VTIGEREXCELFILEPATH);
		}
		else
		{
			javausability.printStatement("product not created ------->TC fail");
			excelfileusability.setDataToExcel(SheetName, 2, 2, IConstantUsability.VTIGERSTATUS2);
			excelfileusability.writedataToExcel(IConstantUsability.VTIGEREXCELFILEPATH);

		}
	}

}
