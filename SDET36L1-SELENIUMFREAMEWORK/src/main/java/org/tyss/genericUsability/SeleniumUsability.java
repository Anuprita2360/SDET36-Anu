package org.tyss.genericUsability;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * This class contains all the webdriver actions
 * @author Anuprita Raut
 *
 */
public class SeleniumUsability 
{
	private WebDriver driver;
	private Actions act;
	private WebDriverWait wait;
	private Select select;
	
	/**
	 * This method is used to setup the driver instance
	 * @param browser
	 * @return 
	 */
	public WebDriver setDriver(String browser)
	{
		//run time polymorphism
		if(browser.equals("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if(browser.equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else if(browser.equals("ie"))
		{
			WebDriverManager.iedriver().setup();
			driver=new InternetExplorerDriver();
		}
		else
		{
			System.out.println("you entered wrong browser key in the property file");
		}
		return driver;

	}
	
	/**
	 * This method is used to maximise the browser
	 */
	public void maximiseBrowser()
	{
		driver.manage().window().maximize();
	}
	
	/**
	 * This method is used to wait the page by using implicit wait
	 * @param longTimeout
	 */
	public void implicitWait(long longTimeout)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(longTimeout));
	}
	
	/**
	 * This method is used to navigate the application
	 * @param url
	 */
	
	public void openApplication(String url)
	{
		driver.get(url);
	}
	
	/**
	 * This method is used to initialise the Actions class
	 */
	public void initialiseActions()
	{
		act=new Actions(driver);
	}
	
	/**
	 * This method is used to Mouse hover on Element
	 * @param element
	 */
	public void mouseHoverOnElement(WebElement element)
	{
		act.moveToElement(element).perform();
	}
	
	/**
	 * This method is used to perform right click action on current mouse location
	 */
	public void rightClickAction()
	{
		act.contextClick().perform();
	}
	
	/**
	 * This method is used to close the Particular browser
	 */
	public void closeBrowser()
	{
		driver.quit();
	}
	
	
	/**
	 * This method is used to close particular tab
	 */
	public void closeTab()
	{
		driver.close();
	}
	
	public void switchWindow(String partialText,String strategy)
	{
		Set<String> winIds= driver.getWindowHandles();

		for(String Id:winIds)
		{
			driver.switchTo().window(Id);
			if(strategy.equalsIgnoreCase("url"))
			{
				if(driver.getCurrentUrl().contains(partialText)) {
					break;
				}
			}
			
			else if(strategy.equalsIgnoreCase("title"))
			{
				if(driver.getTitle().contains(partialText)) {
					break;
				}
			}
		}
	}
	
	/**
	 * This method will wait till the element is clickable(custom wait)
	 * @param totalDuration
	 * @param pollingTime
	 * @param element
	 */
	
	public void waitTillElementClickable(int totalDuration,long pollingTime,WebElement element) {
		int currentTime=0;
		while(currentTime<=totalDuration)
		{
			try {
			element.click();
			break;
			}
			catch(Exception e)
			{
				try {
					Thread.sleep(pollingTime);
				}
				catch(Exception e1)
				{
					e1.printStackTrace();
				}
				currentTime++;
			}
		}
	}
	
	/**
	 * This method is used to initialise the Explicit wait or WebDriverWait
	 * @param timeOuts
	 * @param pollingTime
	 */
	
	public void initialliseExplicitwait(long timeOuts,long pollingTime)
	{
		wait = new WebDriverWait(driver, Duration.ofSeconds(timeOuts));
		wait.pollingEvery(Duration.ofMillis(pollingTime));
		wait.ignoring(Exception.class);
	}
	
	/**
	 * This method is used to wait untill ellement is visible
	 * @param element
	 */
	
	public void waitTillElementVisible(WebElement element)
	{
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	/**
	 * This method is used to wait untill element is invisible
	 * @param element
	 */
	
	public void waitTillElementInvisible(WebElement element)
	{
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	/**
	 * This method is used to accept alert popup
	 */
	
	public void jsPopupaccept()
	{
		driver.switchTo().alert().accept();
	}
	
	/**
	 * This method is used to decline alert popup
	 */
	
	public void jsPopdecline()
	{
		driver.switchTo().alert().dismiss();
	}
	
	/**
	 * This method is used to send the data to alert popup
	 * @param data
	 */
	
	public void jsPopupSendData(String data)
	{
		driver.switchTo().alert().sendKeys(data);
	}
	
	/**
	 * This method is used to get the text from alert popup
	 */
	
	public void jsPopupgetText()
	{
		driver.switchTo().alert().getText();
	}
	
	/**
	 * This method is used to handle selectDropDown
	 * @param dropDownElement
	 * @param indexNumber
	 */
	
	public void handleSelectDropdown(WebElement dropDownElement,int indexNumber)
	{
		select=new Select(dropDownElement);
		select.selectByIndex(indexNumber);
	}
	
	/**
	 * This method is used to take the particular element ScreenShot
	 * @param element
	 * @param currentClass
	 * @param javausability
	 */
	
	public void takeScreenShotElement(WebElement element,Object currentClass,JavaUsability javausability)
	{
		File src = element.getScreenshotAs(OutputType.FILE);
		File dst = new File("./elementScreenShot/"+currentClass.getClass().getSimpleName()+javausability.getCurrentDate("dd_MM_yyyy_HH_mm_sss"));
		try {
			FileUtils.copyFile(src, dst);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method is used to Handle Multiple options by Visible Text
	 * @param element
	 * @param text
	 */

	public void HandlingMultipleOptionsByVisibleText(WebElement element,String text)
	{
		select=new Select(element);
		select.selectByVisibleText(text);
	}
	
	/**
	 * This method is used to Handling the frames
	 * @param frames
	 */
	
	public void HandlingFrames(int frames)
	{
		driver.switchTo().frame(frames);
	}
    
}
